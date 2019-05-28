import React from 'react';
import SimpleCrudPage from '../simpleCrudPage/simpleCrudPage';
import { ApiAddresses, FieldType } from '../../commons/enums';

const API_URL = ApiAddresses.ATTRIBUTE;

export const columns = [
  {
    width: 250,
    label: 'ID',
    dataKey: '_id'
  },
  {
    width: 370,
    flexGrow: 1.0,
    label: 'Attribute Name',
    dataKey: 'name'
  },
  {
    width: 500,
    label: 'Description',
    dataKey: 'description'
  }
];

const dialogContent = {
  display: 'inline-block',
  entity: 'Attribute',
  fields: [
    {
      id: 'name',
      label: 'Name',
      type: FieldType.STRING
    },
    {
      id: 'description',
      label: 'Description',
      type: FieldType.STRING
    }
  ]
};

const Attribute = () => (
  <SimpleCrudPage
    apiUrl={ API_URL }
    columns={ columns }
    dialogContent={ dialogContent }
  />
);

export default Attribute;
