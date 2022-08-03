import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getCharacters, setCharacters } from "./store/slices/rickandmorty";

export const rnm = () => {

  const dispatch = useDispatch();
  const { isLoading, page, characters } = useSelector( state => state.rnm)

  useEffect(() => {
    dispatch( getCharacters(page) );
  }, [page])
  
  return (
    <>
      <h1>Rick and Morty</h1>
      <hr />
      <span>Loading: { isLoading ? 'True': 'False' } </span>

      <ul>
        {
        characters && characters.map( ({ id, name }) => ( 
          <li key= { id }> { name } </li>
        ))
        }
      </ul>
      <button type="button"
      disabled = { isLoading }
      onClick = { () => dispatch( setCharacters({page: page + 1}) )}
      >  
        Next
      </button>
    </>
  )
}

export default rnm