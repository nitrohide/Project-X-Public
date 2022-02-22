import React from "react";
import { deleteProduct, updateProduct } from '../service/StudentService';
import CardLayout from "./CardLayout";

// Card Interface Contains All Card Rendering, Grouping, Functions, and Modals
function CardInterface(props) {

  // Delete Customer Card
  const handleDelete = (id) => {
    let ok = window.confirm('Are you sure you wish to delete this product?\nPress "Ok" to confirm');
    if (ok === true) {
      var removeCard = document.getElementById('card' + id);
      removeCard.parentNode.removeChild(removeCard);
      deleteProduct(id);
      window.alert('product has been deleted');
    }
  }

  // Update Student Card
  const handleUpdate = (product) => {
    updateProduct(product);
  }

  // Render the Cards and the Modals - one for Search mode and one for regular.
  return (
    <div className='container-fluid'>
      {!props.search && <CardLayout search={false} handleUpdate={handleUpdate} handleDelete={handleDelete} />}
      {props.search && <CardLayout search={props.search} searchFor={props.searchFor} handleUpdate={handleUpdate} handleDelete={handleDelete} />}
    </div>
  );
}

export default CardInterface;