import React, { PureComponent } from 'react';
import { TextField } from '@material-ui/core';
import MenuItem from '@material-ui/core/MenuItem';
import { FieldType } from '../../commons/enums';
import Modal from '@material-ui/core/Modal';
import Typography from '@material-ui/core/Typography';
import withStyles from '@material-ui/core/styles/withStyles';
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';
import CancelIcon from '@material-ui/icons/Cancel';
import AddIcon from '@material-ui/icons/Add';

import './editDialog.css';
import axios from 'axios';
import LoadingResults from '../loadingResult/loadingResults';
import EmptyResult from '../emptyResult/emptyResult';
import VirtualizedTable from '../table/virtualizedTable';

function getModalStyle() {
  const top = '40%';
  const left = '47%';

  return {
    top: top,
    left: left,
    transform: `translate(-${ top }, -${ left })`
  };
}

function getObjectModalStyle() {
  return {
    marginLeft: '5%',
    marginTop: '10px',
    width: '85%'
  };
}

const styles = theme => ({
  paper: {
    position: 'absolute',
    width: theme.spacing.unit * 60,
    backgroundColor: theme.palette.background.paper,
    boxShadow: theme.shadows[5],
    padding: theme.spacing.unit * 4,
    outline: 'none'
  },
  button: {
    margin: theme.spacing.unit
  },
  leftIcon: {
    marginRight: theme.spacing.unit,
    fontSize: 20
  },
  buttonFooter: {
    textAlign: 'right',
    margin: '20px 0 -20px 0'
  },
  enumField: {
    width: '90%'
  }
});

class RenderSelectedItems extends PureComponent {
  constructor(props) {
    super(props);

    this.renderSelectedItem = this.renderSelectedItem.bind(this);
  }

  renderSelectedItem(id, item, onChangeData, removeItemFromArray) {
    return (
      <div
        key={ item }
        id={ id }
        onClick={ (e) => removeItemFromArray(e, item, onChangeData)}
      >
        { item }
      </div>
    );
  }

  render() {
    const { id, selectedItems, onChangeData, removeItemFromArray } = this.props;
    return (
      <div
        id={ id }
        className="selected-items"
      >
        {
          selectedItems.map(item =>
            this.renderSelectedItem(id, (item instanceof Object) ? item.name : item, onChangeData, removeItemFromArray))
        }
      </div>
    );
  }
}

const RenderTextField = (props) => (
  <TextField
    id={ props.id }
    name={ props.id }
    label={ props.label }
    type='string'
    onChange={ (e) => props.onChangeData(e.target.name, e.target.value) }
    value={ props.value }
    fullWidth
  />
);

class RenderStringArrayField extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      selectedItems: []
    };

    this.addItemToArray = this.addItemToArray.bind(this);
    this.removeItemFromArray = this.removeItemFromArray.bind(this);
  }

  removeItemFromArray(e, item, onChangeData) {
    e.preventDefault();
    const name = e.target.name;
    this.setState({
      ...this.state,
      selectedItems: this.state.selectedItems.filter(el => el !== item)
    }, () => onChangeData(name, this.state.selectedItems))
  }

  addItemToArray(e, onChangeData) {
    e.preventDefault();
    const item = e.target.value;
    const name = e.target.name;
    this.setState({
      selectedItems: [
        ...this.state.selectedItems,
        item
      ]
    }, () => onChangeData(name, this.state.selectedItems))
  }

  render() {
    const { id, label, onChangeData, value } = this.props;
    const { selectedItems } = this.state;

    return (
      <div className="string-array-field">
        <TextField
          id={ id }
          name={ id }
          label={ label }
          type='string'
          value={ value }
          onKeyPress={(e) => {
            if (e.key === 'Enter') {
              this.addItemToArray(e, onChangeData)
            }
          }}
          fullWidth
        />
        <RenderSelectedItems
          id={ id }
          onChangeData={ onChangeData }
          selectedItems={ selectedItems }
          removeItemFromArray={ this.removeItemFromArray }
        />
      </div>
    );
  }
}

class RenderEnumField extends PureComponent {

  constructor(props) {
    super(props);

    this.state = {
      currentSelected: this.props.value
    };

    this.changeSelected = this.changeSelected.bind(this);
  };

  changeSelected(e, onChangeData) {
    this.setState({
      currentSelected: e.target.value
    });
    onChangeData(e.target.name, e.target.value);
  }

  render() {
    const { id, value, values, label, onChangeData } = this.props;
    const { currentSelected } = this.state;

    return (
      <TextField
        id={ id }
        name={ id }
        className="enum-select"
        select
        value={ currentSelected }
        label={ label }
        onChange={ (e) => this.changeSelected(e, onChangeData) }
        margin="normal"
      >
        {
          values.map(item => (
            <MenuItem
              key={ item.name }
              value={ item.name }
              selected={ item.name === value }
            >
              { item.description }
            </MenuItem>
          ))
        }
      </TextField>
    );
  }
}

const OBJECT_MODAL_INITIAL_STATE = {
  data: [],
  isLoading: true
};

class SelectObjectModal extends PureComponent {

  constructor(props) {
    super(props);

    this.state = OBJECT_MODAL_INITIAL_STATE;

    this.getComponentToRender = this.getComponentToRender.bind(this);
    this.handleGet = this.handleGet.bind(this);
  };

  componentWillMount() {
    this.handleGet();
  }

  componentDidMount() {
    this.handleGet();
  }

  getComponentToRender(data, isLoading, columns, apiUrl, onSelect) {

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
      onRowClick={ onSelect }
      isModal={ true }
    />;

  }

  handleGet() {
    const { objectAddress } = this.props;

    if (objectAddress) {
      this.setState(OBJECT_MODAL_INITIAL_STATE);

      axios.get(objectAddress)
        .then(response => this.setState({
          ...this.state,
          isLoading: false,
          data: response.data
        }));
    }
  }

  render() {
    const { label, isOpen, onClose, classes, columns, objectAddress, onSelect, name } = this.props;
    const { isLoading, data } = this.state;

    if (isOpen) {
      return (
        <Modal
          name={ name }
          open={ isOpen }
          onClose={ onClose }
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
        >
          <div style={ getObjectModalStyle() } className={ classes.paper }>
            <Typography variant="h6" id="modal-title">
              { `Select ${ label } Dialog` }
            </Typography>

            { this.getComponentToRender(data, isLoading, columns, objectAddress, onSelect) }
          </div>

        </Modal>
      );
    }
    return null;
  }
}
const WrappedSelectObjectModal = withStyles(styles)(SelectObjectModal);

class RenderSelectObjectField extends PureComponent {

  constructor(props) {
    super(props);

    this.state = {
      isSelectionModalOpen: false,
      selectedItems: []
    };

    this.handleOnClose = this.handleOnClose.bind(this);
    this.handleOnOpen = this.handleOnOpen.bind(this);
    this.onSelect = this.onSelect.bind(this);
    this.removeItemFromArray = this.removeItemFromArray.bind(this);
  };

  handleOnOpen() {
    this.setState({
      ...this.state,
      isSelectionModalOpen: true
    });
  }

  handleOnClose() {
    this.setState({
      ...this.state,
      isSelectionModalOpen: false
    });
  }

  onSelect(e) {
    const { onChangeData } = this.props;

    window.console.log('teste');
    window.console.log(e);
    window.console.log(e.rowData);
    window.console.log(e.rowData.data);

    const name = e.rowData.data.filter(e => e.label === 'objectName');

    const selectedItem = [
      ...this.state.selectedItems,
      e.rowData.data
    ];

    this.setState({
      selectedItems: selectedItem
    }, () => onChangeData(name, selectedItem, this.handleOnClose));
  }

  removeItemFromArray(e, item, onChangeData) {
    const name = e.target.id;

    this.setState({
      ...this.state,
      selectedItems: this.state.selectedItems.filter(el => el.name !== item)
    }, () => onChangeData(name, this.state.selectedItems))
  }

  render() {
    const { id, label, onChangeData, value, objectAddress, columns, classes } = this.props,
      { isSelectionModalOpen, selectedItems } = this.state;

    return (
      <div className="string-array-field">
        <Button variant="contained"
                size="small"
                className={ classes.button }
                onClick={ this.handleOnOpen }
        >
          <AddIcon className={ classes.leftIcon }/>
          { `Add ${label}` }
        </Button>
        <RenderSelectedItems
          id={ id }
          onChangeData={ onChangeData }
          selectedItems={ selectedItems }
          removeItemFromArray={ this.removeItemFromArray }
        />
        <WrappedSelectObjectModal
          id={ id }
          name={ id }
          label={ label }
          value={ value }
          onSelect={ this.onSelect }
          onClose={ this.handleOnClose }
          isOpen={ isSelectionModalOpen }
          objectAddress={ objectAddress }
          columns={ columns }
        />
      </div>
    );
  }
}

const WrappedRenderSelectObjectField = withStyles(styles)(RenderSelectObjectField);

class RenderDialogField extends PureComponent {
  render() {
    const { id, type, label, onChangeData, value, arrayValues, objectAddress, columns } = this.props;

    switch (type) {
      case FieldType.STRING:
        return (
          <RenderTextField
            id={ id }
            label={ label }
            onChangeData={ onChangeData }
            value={ value }
          />
        );
      case FieldType.ENUM:
        return (
          <RenderEnumField
            id={ id }
            label={ label }
            onChangeData={ onChangeData }
            selectedValue={ value }
            values={ arrayValues }
          />
        );
      case FieldType.STRING_ARRAY:
        return (
          <RenderStringArrayField
            id={ id }
            label={ label }
            onChangeData={ onChangeData }
            value={ value }
          />
        );
      case FieldType.OBJECT:
        return (
          <WrappedRenderSelectObjectField
            id={id}
            label={label}
            onChangeData={ onChangeData }
            selectedValue={ value }
            objectAddress={ objectAddress }
            columns={ columns }
          />
        );
      default:
        return null;
    }
  }
};

const RenderDialogFields = (props) => {
  const { fields, onChangeData } = props;
  return (
    fields.map(item =>
      <RenderDialogField
        key={ item.id }
        id={ item.id }
        label={ item.label }
        type={ item.type }
        onChangeData={ onChangeData }
        arrayValues={ item.arrayValues }
        objectAddress={ item.objectAddress }
        columns={ item.columns }
      />
    )
  );
};

class EditDialog extends PureComponent {

  constructor(props) {
    super(props);

    this.state = {
      data: []
    };

    this.updateData = this.updateData.bind(this);
    this.doPost = this.doPost.bind(this);
    EditDialog.convertToFlattenData = EditDialog.convertToFlattenData.bind(this);
  };

  updateData(field, value, funcAfterUpdate) {
    let alreadyExists = false;

    this.state.data.forEach(data => {
      if (data.field === field) {
        this.setState({
          data: this.state.data.map(x => {
            if (x.field === field) {
              return {
                field: field,
                value: value
              };
            } else {
              return x;
            }
          })
        });
        alreadyExists = true;
      }
    });

    if (!alreadyExists) {
      this.setState({
        ...this.state,
        data: [
          ...this.state.data,
          {
            field: field,
            value: value
          }
        ]
      });
    }

    if (funcAfterUpdate) {
      funcAfterUpdate();
    }
  }

  static convertToFlattenData(data) {
    let payloadString = '{';
    data.forEach((item, index) => {
      const value = (item.value instanceof Array) ?
        `[${ '"' + item.value.join('","') + '"'}]` :
        `"${ item.value }"`;

      payloadString = `${ payloadString } "${ item.field }": ${value}`;
      payloadString = (index + 1 < data.length) ? `${ payloadString }, ` : `${ payloadString } }`;
    });
    return JSON.parse(payloadString);
  }

  doPost(postUrl, handlePost, showSuccessDialog) {
    const data = EditDialog.convertToFlattenData(this.state.data);
    handlePost(postUrl, data, showSuccessDialog);
  }

  render() {
    const { classes, isOpen, onClose, dialogContent: { fields, entity }, handlePost, postUrl, showSuccessDialog } = this.props;

    // check if there something to render
    if (isOpen && fields && fields.length > 0) {

      return (
        <Modal
          open={ isOpen }
          onClose={ onClose }
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
        >
          <div style={ getModalStyle() } className={ classes.paper }>
            <Typography variant="h6" id="modal-title">
              { `Create/Edit ${ entity } Dialog` }
            </Typography>

            <RenderDialogFields
              fields={ fields }
              onChangeData={ this.updateData }
            />

            <footer className={ classes.buttonFooter }>
              <Button variant="contained"
                      size="small"
                      className={ classes.button }
                      onClick={ onClose }
              >
                <CancelIcon className={ classes.leftIcon }/>
                Cancel
              </Button>
              <Button
                variant="contained"
                size="small"
                className={ classes.button }
                onClick={ () => this.doPost(postUrl, handlePost, showSuccessDialog) }
              >
                <SaveIcon className={ classes.leftIcon }/>
                Save
              </Button>
            </footer>
          </div>

        </Modal>
      );
    }
    return null;
  }
}

export default withStyles(styles)(EditDialog);
