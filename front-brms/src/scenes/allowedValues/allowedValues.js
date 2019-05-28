import React from 'react';
import SimpleCrudPage from '../simpleCrudPage/simpleCrudPage';

const API_URL = 'http://localhost:8080/api/v1/allowed/attribute';

const columns = [
  {
    width: 250,
    label: 'Attribute Name',
    dataKey: 'attribute'
  },
  {
    width: 300,
    flexGrow: 1.0,
    label: 'Description',
    dataKey: 'description'
  },
  {
    width: 500,
    label: 'Allowed Values',
    dataKey: 'allowedValues'
  }
];

const AllowedValues = () => (
  <SimpleCrudPage
    apiUrl={ API_URL }
    columns={ columns }
  />
);

export default AllowedValues;
