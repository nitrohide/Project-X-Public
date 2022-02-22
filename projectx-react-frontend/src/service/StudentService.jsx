import { useState, useEffect } from 'react';
import axios from 'axios';

const baseurl = 'http://localhost:8080';

// custom hook for performing GET request
export const useFetch = (searchMethod, searchValue) => {
  let param = 0;

  if (searchValue !== undefined) param = searchValue;

  const getAll = `${baseurl}/api/products`;
  const getById = `${baseurl}/api/products/` + param;
  const search = `${baseurl}/api/product/price/max/` + param;
  let url = null;

  switch (searchMethod) {
    case 'getById': url = getById;
      break;
    case 'getAll': url = getAll;
      break;
    case 'search': url = search;
      break;
    default: url = null;
  }

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    const fetchData = async function () {
      try {
        setLoading(true);
        const response = await axios.get(url);
        if (response.status === 200) {
          setData(response.data);
          console.log('Date fetched! -> ' + data[0]);
        }
      } catch (error) {
        setData(false);
        throw error;
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [url]);
  return { loading, data };
};

export function createProduct(product) {
  return axios.post(`${baseurl}/api/product/add`, product);
}

export function updateProduct(product) {
  console.log('in the update service');
  return axios.put(`${baseurl}/api/product/update`, product);
}

export function deleteProduct(id) {
  return axios.delete(`${baseurl}/api/product/delete/` + id);
}




