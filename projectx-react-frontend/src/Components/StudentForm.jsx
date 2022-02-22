import React, { Component } from 'react';
import './form.css';

class StudentForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            productID: props.product.productID,
            productName: props.product.productName,
            price: props.product.productPrice,
            category: props.product.productCategory,
            description: props.product.productDescription,
            quantity: props.product.productQuantity
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({ [nam]: val });
    }

    handleSubmit = (event) => {
        console.log('submit');
        this.props.handleCrud(this.state);
    }

    render() {
        console.log(this.state);
        return (
            <div>
                <div className="form-style">
                    {this.state.id && <h3>{'Changes for ' + this.state.productName + ' '}</h3>}
                    <form onSubmit={this.handleSubmit}>
                        <div className='row d-inline-flex'>

                        <div className="form-group col">
                                <label >Product ID:</label>
                                <input type="text" readOnly={true} name="productID" className="form-control" id="productID" value={0} onChange={this.handleChange} required />
                            </div>

                            <div className="form-group col">
                                <label >Product Name:</label>
                                <input type="text" name="productName" className="form-control" id="productName" value={this.state.productName} onChange={this.handleChange} required />
                            </div>

                            <div className="form-group col">
                                <label >Price:</label>
                                <input type="number" step="0.01" name="price" className="form-control" id="price" value={this.state.price} onChange={this.handleChange} required />
                            </div>
                        </div>

                        <div className="form-group">
                            <label >Category:</label>
                            <input type="text" name="category" className="form-control" id="category" value={this.state.category} onChange={this.handleChange} required />
                        </div>

                        <div className="form-group">
                            <label >Description:</label>
                            <input type="text" name="description" className="form-control" id="description" value={this.state.description} onChange={this.handleChange} required />
                        </div>

                        <div className="form-group">
                            <label >Quantity:</label>
                            <input type="text" name="quantity" className="form-control" id="quantity" value={this.state.quantity} onChange={this.handleChange} required />
                        </div>

                        <input type="submit" value="Submit" className="btn btn-primary" />
                    </form>

                </div>
            </div>
        );
    }
}
export default StudentForm;