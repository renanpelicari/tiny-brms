import React, { PureComponent } from 'react';
import { createMuiTheme } from '@material-ui/core/styles';
import blue from '@material-ui/core/colors/blue';
import { MuiThemeProviderOld } from '@material-ui/core/es/styles/MuiThemeProvider';
import withStyles from '@material-ui/core/styles/withStyles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import { Context } from '../../commons/enums';
import Attribute from '../attribute/attribute';
import Matcher from '../matcher/matcher';
import Rule from '../rule/rule';
import AllowedValues from '../allowedValues/allowedValues';
import DrawerMenu from '../drawerMenu/drawerMenu';
import MenuIcon from '@material-ui/icons/Menu';
import HomeIcon from '@material-ui/icons/Home';
import AttributeIcon from '@material-ui/icons/List';
import MatcherIcon from '@material-ui/icons/AssignmentTurnedIn';
import RuleIcon from '@material-ui/icons/Ballot';
import AllowedValuesIcon from '@material-ui/icons/PlaylistAddCheck';
import RuleValidation from '@material-ui/icons/Beenhere';

const contexts = {
  attribute: {
    title: 'Attributes',
    component: <Attribute/>,
    link: '/attributes',
    iconMenu: <AttributeIcon/>
  },
  matcher: {
    title: 'Matchers',
    component: <Matcher/>,
    link: '/matchers',
    iconMenu: <MatcherIcon/>
  },
  rule: {
    title: 'Rules',
    component: <Rule/>,
    link: '/rules',
    iconMenu: <RuleIcon/>
  },
  allowedValues: {
    title: 'Allowed Values',
    component: <AllowedValues/>,
    link: '/allowedValues',
    iconMenu: <AllowedValuesIcon/>
  },
  ruleValidation: {
    title: 'Validation Rules',
    link: '/ruleValidation',
    iconMenu: <RuleValidation/>
  },
  home: {
    title: 'Home',
    link: '/',
    iconMenu: <HomeIcon/>
  }
};

const defaultTheme = createMuiTheme({
  palette: {
    primary: blue,
    secondary: blue
  }
});

const menuItems = [
  contexts.home,
  contexts.attribute,
  contexts.matcher,
  contexts.rule,
  contexts.allowedValues
];

function getContextData(context) {

  switch (context) {

    case Context.ATTRIBUTE:
      return contexts.attribute;

    case Context.MATCHER:
      return contexts.matcher;

    case Context.RULE:
      return contexts.rule;

    case Context.RULE_VALIDATION:
      return contexts.ruleValidation;

    case Context.ALLOWED_VALUES:
      return contexts.allowedValues;

    default:
      return contexts.home;
  }
}

class Main extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      isMenuOpen: false
    };

    this.openCloseMenu = this.openCloseMenu.bind(this);
  };

  openCloseMenu(isOpen) {
    this.setState({
      ...this.state,
      isMenuOpen: !isOpen
    });
  }

  render() {
    const { classes, context } = this.props;
    const { title, component } = getContextData(context);
    const { isMenuOpen } = this.state;

    return (
      <main>
        <MuiThemeProviderOld theme={ defaultTheme }>
          <div className={ classes.root }>
            <AppBar position="static">
              <Toolbar>
                <IconButton className={ classes.menuButton } color="inherit" aria-label="Menu">
                  <MenuIcon
                    onClick={ () => this.openCloseMenu(isMenuOpen) }
                  />
                  <DrawerMenu
                    isOpen={ isMenuOpen }
                    openCloseMenu={ this.openCloseMenu }
                    menuItems={ menuItems }
                  />
                </IconButton>
                <Typography variant="h6" color="inherit" className={ classes.grow }>
                  { title }
                </Typography>
              </Toolbar>
            </AppBar>
          </div>

          { component }
        </MuiThemeProviderOld>
      </main>
    );
  }
}

export default withStyles(defaultTheme)(Main);
