import React, { Component } from 'react';
import VirtualizedTable from '../table/virtualizedTable';
import axios from 'axios';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import CloseIcon from '@material-ui/icons/Close';
import Icon from '@material-ui/core/Icon';
import LoadingResults from '../loadingResult/loadingResults';
import EmptyResult from '../emptyResult/emptyResult';
import SuccessDialog from '../successDialog/successDialog';
import EditDialog from '../editDialog/editDialog';
import { withStyles } from '@material-ui/core';

const styles = theme => ({
  itemSelectedDiv: {
    width: '100%',
    display: 'inline-flex',
    backgroundColor: '#2196f3',
    color: '#fff'
  },
  divIconCancel: {
    display: 'inline-flex'
  },
  selectedItemText: {
    display: 'inline-flex',
    margin: '4px',
    width: '83%'
  },
  iconCancel: {
    margin: '4px'
  },
  addButton: {
    margin: theme.spacing.unit
  },
  deleteButton: {
    margin: theme.spacing.unit,
    backgroundColor: '#616161'
  },
  editButton: {
    margin: theme.spacing.unit,
    backgroundColor: '#f50057'
  },
  buttonPositioning: {
    direction: 'rtl',
    marginRight: theme.spacing.unit * 3
  }
});

const INITIAL_STATE = {
  isLoading: true,
  data: [],
  isDialogOpen: false,
  isSuccessDialogOpen: false,
  anyRowSelected: false,
  selectedId: 0,
  successMessage: ''
};

class SimpleCrudPage extends Component {
  constructor(props) {
    super(props);

    this.state = INITIAL_STATE;

    this.getComponentToRender = this.getComponentToRender.bind(this);
    this.showAddIcon = this.showAddIcon.bind(this);
    this.showEditDeleteIcon = this.showEditDeleteIcon.bind(this);
    this.changeDialogState = this.changeDialogState.bind(this);
    this.changeSuccessDialogState = this.changeSuccessDialogState.bind(this);
    this.closeDialog = this.closeDialog.bind(this);
    this.closeSuccessDialog = this.closeSuccessDialog.bind(this);
    this.showSuccessDialog = this.showSuccessDialog.bind(this);
    this.handleGet = this.handleGet.bind(this);
    this.handleDelete = this.handleDelete.bind(this);
    this.handlePost = this.handlePost.bind(this);
    this.onSelectRow = this.onSelectRow.bind(this);
    this.onUnselectRow = this.onUnselectRow.bind(this);
  };

  componentWillMount() {
    this.handleGet();
  }

  componentDidMount() {
    this.handleGet();
  }

  handleGet() {
    this.setState(INITIAL_STATE);

    axios.get(this.props.apiUrl)
      .then(response => this.setState({
        ...this.state,
        isLoading: false,
        data: response.data
      }));
  }

  handlePost(url, data, showSuccessDialog) {
    axios.post(url, data)
      .then(() => showSuccessDialog('created successfully'));
  }

  handleDelete(url, id, showSuccessDialog) {
    axios.delete(`${ url }/${ id }`)
      .then(() => showSuccessDialog('deleted successfully'));
  }

  changeDialogState() {
    this.setState({
      ...this.state,
      isDialogOpen: !this.state.isDialogOpen
    });
  }

  changeSuccessDialogState() {
    this.setState({
      ...this.state,
      isSuccessDialogOpen: !this.state.isSuccessDialogOpen
    });
  }

  closeDialog() {
    this.setState({
      ...this.state,
      isDialogOpen: false
    });
    this.handleGet();
  }

  closeSuccessDialog() {
    this.setState({
      ...this.state,
      isSuccessDialogOpen: false
    });
    this.handleGet();
  }

  showSuccessDialog(message) {
    this.setState({
      ...this.state,
      isDialogOpen: false,
      isSuccessDialogOpen: true,
      successMessage: message
    });
  }

  getComponentToRender(data, isLoading, columns, apiUrl) {

    if (isLoading) {
      return <LoadingResults/>;
    }

    if (data.length === 0) {
      return <EmptyResult/>;
    }

    return <VirtualizedTable
      columns={ columns }
      rows={ data }
      apiUrl={ apiUrl }
      handleDelete={ this.handleDelete }
      showSuccessDialog={ this.showSuccessDialog }
      onRowClick={ this.onSelectRow }
    />;

  }

  showEditDeleteIcon() {
    const { selectedId } = this.state;
    const { classes, apiUrl } = this.props;

    return (
      <div className={ classes.itemSelectedDiv }>
        <div className={ classes.divIconCancel } onClick={ () => this.onUnselectRow() }>
          <CloseIcon className={ classes.iconCancel }/>
        </div>

        <div className={ classes.selectedItemText }>
          { `ID Selected: ${ selectedId }` }
        </div>

        <div className={ classes.buttonPositioning }>
          <Fab
            color="primary"
            aria-label="Delete"
            className={ classes.deleteButton }
            onClick={ () => this.handleDelete(apiUrl, selectedId, this.showSuccessDialog) }
          >
            <DeleteIcon/>
          </Fab>
          <Fab
            disabled
            color="primary"
            aria-label="Edit"
            className={ classes.editButton }
          >
            <Icon>edit_icon</Icon>
          </Fab>
        </div>
      </div>
    );
  }

  showAddIcon() {
    const { classes } = this.props;
    return (
      <div className={ classes.buttonPositioning }>
        <Fab
          color="primary"
          aria-label="Add"
          className={ classes.addButton }
          onClick={ () => this.changeDialogState() }
        >
          <AddIcon/>
        </Fab>
      </div>
    );
  }

  onUnselectRow() {
    this.setState({
      anyRowSelected: false,
      selectedId: null
    });
  }

  onSelectRow(event) {
    this.setState({
      anyRowSelected: true,
      selectedId: event.rowData._id
    });
  }

  render() {
    const { data, isLoading, isDialogOpen, isSuccessDialogOpen, anyRowSelected, successMessage } = this.state;
    const { columns, dialogContent, apiUrl } = this.props;

    const actionButtons = (anyRowSelected) ?
      this.showEditDeleteIcon() :
      this.showAddIcon();

    return (
      <div>
        { this.getComponentToRender(data, isLoading, columns, apiUrl) }
        { actionButtons }
        <EditDialog
          isOpen={ isDialogOpen }
          onClose={ this.closeDialog }
          handlePost={ this.handlePost }
          dialogContent={ dialogContent }
          showSuccessDialog={ this.showSuccessDialog }
          postUrl={ apiUrl }
        />
        <SuccessDialog
          isOpen={ isSuccessDialogOpen }
          onClose={ this.closeSuccessDialog }
          message={ `${ dialogContent.entity } ${ successMessage }` }
        />
      </div>
    );
  }
}

export default withStyles(styles)(SimpleCrudPage);
