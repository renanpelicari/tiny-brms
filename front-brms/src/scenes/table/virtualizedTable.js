/* eslint-disable no-console */

import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import TableCell from '@material-ui/core/TableCell';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Paper from '@material-ui/core/Paper';

import { AutoSizer, Column, SortDirection, Table } from 'react-virtualized';

const styles = theme => ({
  table: {
    fontFamily: theme.typography.fontFamily
  },
  flexContainer: {
    display: 'flex',
    alignItems: 'center',
    boxSizing: 'border-box'
  },
  tableRow: {
    cursor: 'pointer'
  },
  tableRowHover: {
    '&:hover': {
      backgroundColor: theme.palette.grey[200]
    }
  },
  tableCell: {
    flex: 1
  },
  noClick: {
    cursor: 'initial'
  }
});

class MuiVirtualizedTable extends React.PureComponent {

  constructor(props) {
    super(props);

    MuiVirtualizedTable.getDataValue = MuiVirtualizedTable.getDataValue.bind(this);
    this.getRowClassName = this.getRowClassName.bind(this);
    this.cellRenderer = this.cellRenderer.bind(this);
  };

  getRowClassName = ({ index }) => {
    const { classes, rowClassName, onRowClick } = this.props;

    return classNames(classes.tableRow, classes.flexContainer, rowClassName, {
      [classes.tableRowHover]: index !== -1 && onRowClick != null
    });
  };

  static getDataValue(cellData, column) {

    if (cellData instanceof Object) {

      if (!!column.func) {
        return column.func(cellData);
      }

      return JSON.stringify(cellData).match(/[a-zA-Z0-9:,]/g);
    }
    return cellData;
  }

  cellRenderer = ({ cellData, columnIndex = null }) => {
    const { columns, classes, rowHeight, onRowClick } = this.props;
    const dataValue = MuiVirtualizedTable.getDataValue(cellData, columns[columnIndex]);

    return (
      <TableCell
        component="div"
        className={ classNames(classes.tableCell, classes.flexContainer, {
          [classes.noClick]: onRowClick == null
        }) }
        variant="body"
        style={ { height: rowHeight } }
        align={ (columnIndex != null && columns[columnIndex].numeric) || false ? 'right' : 'left' }
      >
        { dataValue }
      </TableCell>
    );
  };

  headerRenderer = ({ label, columnIndex, dataKey, sortBy, sortDirection }) => {
    const { headerHeight, columns, classes, sort } = this.props;
    const direction = {
      [SortDirection.ASC]: 'asc',
      [SortDirection.DESC]: 'desc'
    };

    const inner =
      !columns[columnIndex].disableSort && sort != null ? (
        <TableSortLabel active={ dataKey === sortBy } direction={ direction[sortDirection] }>
          { label }
        </TableSortLabel>
      ) : (
        label
      );

    return (
      <TableCell
        component="div"
        className={ classNames(classes.tableCell, classes.flexContainer, classes.noClick) }
        variant="head"
        style={ { height: headerHeight } }
        align={ columns[columnIndex].numeric || false ? 'right' : 'left' }
      >
        { inner }
      </TableCell>
    );
  };

  render() {
    const { classes, columns, handleDelete, apiUrl, ...tableProps } = this.props;
    return (
      <AutoSizer>
        { ({ height, width }) => (
          <Table
            className={ classes.table }
            height={ height }
            width={ width }
            { ...tableProps }
            rowClassName={ this.getRowClassName }
          >
            { columns.map(({ cellContentRenderer = null, className, dataKey, ...other }, index) => {
              let renderer;

              if (cellContentRenderer != null) {
                renderer = cellRendererProps => {
                  this.cellRenderer({
                    cellData: cellContentRenderer(cellRendererProps),
                    columnIndex: index
                  });
                };
              } else {
                renderer = this.cellRenderer;
              }

              return (
                <Column
                  key={ dataKey }
                  headerRenderer={ headerProps =>
                    this.headerRenderer({
                      ...headerProps,
                      columnIndex: index
                    })
                  }
                  className={ classNames(classes.flexContainer, className) }
                  cellRenderer={ renderer }
                  dataKey={ dataKey }
                  { ...other }
                />
              );
            }) }
          </Table>
        ) }
      </AutoSizer>
    );
  }
}

MuiVirtualizedTable.propTypes = {
  classes: PropTypes.object.isRequired,
  columns: PropTypes.arrayOf(
    PropTypes.shape({
      cellContentRenderer: PropTypes.func,
      dataKey: PropTypes.string.isRequired,
      width: PropTypes.number.isRequired
    })
  ).isRequired,
  headerHeight: PropTypes.number,
  onRowClick: PropTypes.func,
  rowClassName: PropTypes.string,
  rowHeight: PropTypes.oneOfType([PropTypes.number, PropTypes.func]),
  sort: PropTypes.func
};

MuiVirtualizedTable.defaultProps = {
  headerHeight: 56,
  rowHeight: 56
};

const WrappedVirtualizedTable = withStyles(styles)(MuiVirtualizedTable);

class VirtualizedTable extends PureComponent {

  constructor(props) {
    super(props);

    this.state = {
      isLoading: true,
      rows: this.props.rows
    };
  };

  render() {
    const { columns, onRowClick, isModal } = this.props;
    const { rows } = this.state;

    const style = (isModal) ?
      { height: 300, width: '100%' } :
      { height: 500, width: '100%' };

    return (
      <Paper style={ style }>
        <WrappedVirtualizedTable
          rowCount={ rows.length }
          rowGetter={ ({ index }) => rows[index] }
          onRowClick={ event => onRowClick(event) }
          columns={ columns }
          isModal={ isModal }
        />
      </Paper>
    );
  }
}

VirtualizedTable.propTypes = {
  columns: PropTypes.arrayOf(
    PropTypes.shape({
      cellContentRenderer: PropTypes.func,
      dataKey: PropTypes.string.isRequired,
      width: PropTypes.number.isRequired
    })
  ).isRequired,
  rows: PropTypes.array,
  handleDelete: PropTypes.func
};

export default VirtualizedTable;
