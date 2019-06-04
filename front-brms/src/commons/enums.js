const Context = {
  MAIN: 'main',
  ATTRIBUTE: 'attribute',
  ALLOWED_VALUES: 'allowedValues',
  MATCHER: 'matcher',
  RULE: 'rule',
  RULE_VALIDATION: 'ruleValidation'
};
Object.freeze(Context);

const FieldType = {
  STRING: 'string',
  ENUM: 'enum',
  STRING_ARRAY: 'string_array',
  OBJECT: 'object'
};

const ExpressionEnum = {
  BETWEEN_INTEGER: { name: 'BETWEEN_INTEGER', description: 'Between (Integer)' },
  BETWEEN_DOUBLE: { name: 'BETWEEN_DOUBLE', description: 'Between (Double)' },
  CONTAINS: { name: 'CONTAINS', description: 'Contains' },
  NOT_CONTAINS: { name: 'NOT_CONTAINS', description: 'Not Contains' },
  EQUAL: { name: 'EQUAL', description: 'Equal' },
  EQUAL_NOT_MATCH_CASE: { name: 'EQUAL_NOT_MATCH_CASE', description: 'Equal (not consider letter case)' },
  HAS_IN: { name: 'HAS_IN', description: 'Has In' },
  MORE_THAN: { name: 'MORE_THAN', description: 'More Than' },
  LESS_THAN: { name: 'LESS_THAN', description: 'Less Than' },
  EQUAL_OR_MORE_THAN: { name: 'EQUAL_OR_MORE_THAN', description: 'Equal or More Than' },
  EQUAL_OR_LESS_THAN: { name: 'EQUAL_OR_LESS_THAN', description: 'Equal or Less Than' },
  CONTAINS_ALL: { name: 'CONTAINS_ALL', description: 'Contains All' },
  BEFORE_THAN: { name: 'BEFORE_THAN', description: 'Before Than' },
  AFTER_THAN: { name: 'AFTER_THAN', description: 'After Than' },
  SAME_DAY: { name: 'SAME_DAY', description: 'Same Day' },
  AFTER_DAYS: { name: 'AFTER_DAYS', description: 'After (x) Days' }
};

const ExpressionEnumList = Object.values(ExpressionEnum);

const ApiAddresses = {
  ATTRIBUTE: 'http://localhost:8080/api/v1/attribute',
  MATCHER: 'http://localhost:8080/api/v1/matcher',
  RULE: 'http://localhost:8080/api/v1/rule'
};

export { Context, FieldType, ExpressionEnum, ApiAddresses, ExpressionEnumList };
