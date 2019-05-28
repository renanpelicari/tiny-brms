import React from 'react';
import SimpleCrudPage from '../simpleCrudPage/simpleCrudPage';

import './rule.css';

const API_URL = 'http://localhost:8080/api/v1/rule';

function showValues(matcherValues) {
  return matcherValues.map((item, count) => {
    if (count + 1 < matcherValues.length) {
      return `${ item }, `;
    }
    return item;
  });
}

function showMatcher(matcher) {
  return (
    <div className="rule-matcher-div">
      <div className="rule-matcher-name">{ matcher.name }</div>
      <div className="rule-matcher-attribute-name">{ matcher.attribute.name }</div>
      <div className="rule-matcher-values">{ showValues(matcher.values) }</div>
    </div>
  );
}

function showMatchers(matchers) {
  return (
    <div>
      { matchers.map(item => showMatcher(item)) }
    </div>
  );
}

function showAllowedAttribute(allowedAttribute) {
  return (
    <div className="rule-allows-attr-div">
      <div className="allows-value-name">{ allowedAttribute.attribute }</div>
      <div className="allows-value-values">{ showValues(allowedAttribute.values) }</div>
    </div>
  );
}

function showAllowedValues(allowedValues) {
  return (
    <div>
      { allowedValues.map(item => showAllowedAttribute(item)) }
    </div>
  );
}

const columns = [
  {
    width: 140,
    label: 'Rule',
    dataKey: 'name'
  },
  {
    width: 160,
    label: 'Description',
    dataKey: 'description'
  },
  {
    width: 500,
    label: 'Related Matchers',
    dataKey: 'matchers',
    func: showMatchers
  },
  {
    width: 200,
    flexGrow: 1.0,
    label: 'Allows Values',
    dataKey: 'allowedValues',
    func: showAllowedValues
  }
];

const Rule = () => (
  <SimpleCrudPage
    apiUrl={ API_URL }
    columns={ columns }
  />
);

export default Rule;
