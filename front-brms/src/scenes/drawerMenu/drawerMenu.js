import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import SwipeableDrawer from '@material-ui/core/SwipeableDrawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Link } from 'react-router-dom';

const styles = {
  list: {
    width: 250
  },
  fullList: {
    width: 'auto'
  }
};

const menuIconStyle = {
  display: 'inline-block'
};

const menuTitleStyle = {
  display: 'inline-block'
};

class DrawerMenu extends React.Component {

  render() {
    const { classes, isOpen, openCloseMenu, menuItems } = this.props;

    const sideList = (
      <div className={ classes.list }>
        <List>
          {
            menuItems.map(el => (
              <ListItem button key={ el.title }>
                <Link to={ el.link }
                      style={ { textDecoration: 'none' } }>

                  <ListItemIcon style={ menuIconStyle }>
                    { el.iconMenu }
                  </ListItemIcon>

                  <ListItemText primary={ el.title }
                                style={ menuTitleStyle }/>
                </Link>
              </ListItem>
            ))
          }
        </List>
      </div>
    );

    return (
      <div>
        <SwipeableDrawer
          open={ isOpen }
          onClose={ () => openCloseMenu(isOpen) }
          onOpen={ () => openCloseMenu(isOpen) }
        >
          <div
            tabIndex={ 0 }
            role="button"
            onClick={ () => openCloseMenu(isOpen) }
            onKeyDown={ () => openCloseMenu(isOpen) }
          >
            { sideList }
          </div>
        </SwipeableDrawer>
      </div>
    );
  }
}

DrawerMenu.propTypes = {
  classes: PropTypes.object.isRequired,
  isOpen: PropTypes.bool.isRequired,
  openCloseMenu: PropTypes.func.isRequired,
  menuItems: PropTypes.array.isRequired
};

export default withStyles(styles)(DrawerMenu);
