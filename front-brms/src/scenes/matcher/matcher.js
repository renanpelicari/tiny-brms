import React from 'react';
import SimpleCrudPage from '../simpleCrudPage/simpleCrudPage';
import { ApiAddresses, ExpressionEnumList, FieldType } from '../../commons/enums';
import * as Attribute from '../attribute/attribute';

const API_URL = ApiAddresses.MATCHER;

function showAttribute(attribute) {
  return attribute.name;
}

const columns = [
  {
    width: 0,
    label: '',
    dataKey: '_id'
  },
  {
    width: 150,
    flexGrow: 1.0,
    label: 'Matcher Name',
    dataKey: 'name'
  },
  {
    width: 400,
    label: 'Description',
    dataKey: 'description'
  },
  {
    width: 160,
    label: 'Related Attribute',
    dataKey: 'attribute',
    func: showAttribute
  },
  {
    width: 200,
    label: 'Expression',
    dataKey: 'expression'
  },
  {
    width: 250,
    label: 'Values',
    dataKey: 'values'
  }
];

const dialogContent = {
  display: 'inline-block',
  entity: 'Matcher',
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
    },
    {
      id: 'attribute',
      label: 'Attribute',
      type: FieldType.OBJECT,
      objectAddress: ApiAddresses.ATTRIBUTE,
      columns: Attribute.columns
    },
    {
      id: 'expression',
      label: 'Expression',
      type: FieldType.ENUM,
      arrayValues: ExpressionEnumList
    },
    {
      id: 'values',
      label: 'Values',
      type: FieldType.STRING_ARRAY
    }
  ]
};

const Matcher = () => (
  <SimpleCrudPage
    apiUrl={ API_URL }
    columns={ columns }
    dialogContent={ dialogContent }
  />
);

export default Matcher;
