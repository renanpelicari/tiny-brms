import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { Context } from './commons/enums';
import Main from './scenes/main/main';

ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route path="/" exact={ true } component={ () => <Main context={ Context.MAIN }/> }/>
      <Route path="/allowedValue**" component={ () => <Main context={ Context.ALLOWED_VALUES }/> }/>
      <Route path="/attribute*" component={ () => <Main context={ Context.ATTRIBUTE }/> }/>
      <Route path="/matcher*" component={ () => <Main context={ Context.MATCHER }/> }/>
      <Route path="/rule*" component={ () => <Main context={ Context.RULE }/> }/>
      <Route path="/rule_validation*" component={ () => <Main context={ Context.RULE_VALIDATION }/> }/>
    </Switch>
  </BrowserRouter>
  , document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
