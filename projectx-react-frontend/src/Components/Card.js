import React, { Component } from 'react';
import FormModal from './FormModal';
import './Card.css';

class Card extends Component {

    // Returns sub functions that make up Card Components
    render() {

        return (
            <div className="card bg-secondary text-white rounded col-lg-2 col-12 col-md-12 d-flex align-content-center" id={'card' + this.props.info.productID}>
                <Header productName={this.props.info.productName} />
                <Body description={this.props.info.description} productPrice={this.props.info.price} productID={this.props.info.productID} quantity={this.props.info.quantity} />
                <Footer cardID={'card' + this.props.info.id} product = {this.props.info} productID={this.props.info.productID} handleDelete={this.props.handleDelete} 
                handleUpdate={this.props.handleUpdate} />
            </div>

        );
    }
}

function Header(props) {
    return (
        <div className="card-header">
            <h3><b>{props.productName}</b></h3>
        </div>
    );
}

function Body(props) {
    return (
        <div className="card-body">
            <div className="container">
                <p><b>Description: {props.description}</b></p>
                <p><b>Price: {props.productPrice}</b></p>
                <p><b>Quantity: {props.quantity}</b></p>
                <p><b>Product ID: {props.productID}</b></p>
            </div>
        </div>
    );
}

// Foot calls props functions that lift state up for deletion and updte buttons on the card
function Footer(props) {
    return (
        <div className="card-footer btn-group">
            <button className="btn btn-danger" onClick={props.handleDelete.bind(this, props.productID)}>DELETE</button>
            <FormModal type='update' product={props.product} handleUpdate={props.handleUpdate}/>
        </div>
    );
}
export default Card;