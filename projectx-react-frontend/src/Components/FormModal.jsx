import React, { useState } from 'react';
import { Button, Modal } from 'react-bootstrap';
import StudentForm from './StudentForm';
import { createProduct } from '../service/StudentService';

const FormModal = (props) => {
  const [show, setShow] = useState(false);
  const [type, setType] = useState(props.type);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  var product, buttonColor, buttonText, crudFunction;

  // Switch Allows Modal Reuse for Update and Create
  switch (type) {
    case 'create':
      buttonColor = 'primary';
      buttonText = type.toString().toUpperCase();
      crudFunction = (product) => { createProduct(product); }
      product = {};
      break;
    case 'update':
      buttonColor = 'warning';
      buttonText = type.toString().toUpperCase();
      crudFunction = props.handleUpdate;
      product = props.product;
      break;
    default:
  }

  return (
    <>
      {<Button variant={buttonColor} onClick={handleShow}>
        {buttonText}
      </Button>}

      <Modal show={show} onHide={handleClose} size='lg'>
        <Modal.Header closeButton>
          <Modal.Title>{buttonText + ' Product'}</Modal.Title>
        </Modal.Header>
        <Modal.Body><StudentForm product={product} handleCrud={crudFunction} /></Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
            </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default FormModal;