import React from "react";
import Card from './Card';
import { useFetch } from '../service/StudentService';
import { chunk } from '../service/Utility';

function CardLayout(props) {

  var searchMethod = 'getAll';
  var searchValue = null;

  // If search navabar was submitted, we'll use the search method call from the api to load data
  if (props.search) {
    searchMethod = 'search';
    searchValue = props.search;
  }

  // our custom hook to load into data, hooked into this component's lifecycle
  const { loading, data } = useFetch(searchMethod, searchValue);

  // Call the chunk() method from service
  const makeDecks = (input, size) => {
    return chunk(input, size);
  }

  // If in 'Search View', the back button to see the 'getAll' cards again
  const handleBack = () => {
    props.searchFor(null, null);
  }

  // Render here has conditional rendering for if Loading data, data as an array, data as a single object, and data that either 
  // failed to load or didn't return search result.  Finally the back to main button only renders when in search mode.
  return (
    <div className='pt-3'>
      {loading && <p>loading...</p>}
      {data && data.length > 0 &&
        makeDecks(data, 4).map((deck, idx) => (
          <div key={idx} className='card-deck container-fluid pt-3 row'>
            {deck.map((product, idx) => <Card key={product.productID} info={product} handleDelete={props.handleDelete} handleUpdate={props.handleUpdate} />)}
          </div>))
      }
      {data && !Array.isArray(data) && data['productName'] !== 'N/A' &&
        typeof data == 'object' && <Card info={data} handleDelete={props.handleDelete} handleUpdate={props.handleUpdate} />
      }
      {(data === false || data['productName'] === 'N/A' || data.length === 0) && <h1>Nothing found to matches that search...</h1>}
      {props.search &&
        <button className='btn btn-info pt-3' onClick={handleBack}>Back to Main</button>
      }
    </div>
  );
}

export default CardLayout;