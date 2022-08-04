import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getCharacters, setCharacters } from "../store/slices/rickandmorty";
import React from 'react'

dispatch = useDispatch();
const { isLoading, page, characters } = useSelector( state => state.rnm)

export const Characters = () => {
  return (

    useEffect(() => {
    dispatch( getCharacters(page) );
        }, [page])
    {
        characters && characters.map( ({ id, name }) => ( 
            <li key= { id }> { name } </li>
        ))
        }
  )
}




    // <hr />
    // <span>Loading: { isLoading ? 'True': 'False' } </span>
    //     <button type="button"
    //   disabled = { isLoading }
    //   onClick = { () => dispatch( setCharacters({page: page + 1}) )}
    //   >  
    //     Next
    //   </button>

