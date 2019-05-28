import React from 'react';
import AlertDialog from '../alertDialog/alertDialog';

const SuccessDialog = (props) => (
  <AlertDialog
    title="Success Dialog"
    message={ props.message }
    isOpen={ props.isOpen }
    onClose={ props.onClose }
    hasCancel={ false }
  />
);

export default SuccessDialog;
