import { characterApi } from "../../../api/rnmapi"
import { setCharacters, startLoadingCharacters } from "./rnmSlice"

export const getCharacters = ( page = 1 ) => {
    return async( dispatch, getState ) => {
        dispatch( startLoadingCharacters() );

        // const resp = await fetch(`https://rickandmortyapi.com/api/character/?page=${page}`)
        // const data = await resp.json();
        // console.log(data);

        const { data } = await characterApi.get(`character/?page=${page}`)
        console.log(data);

        dispatch( setCharacters({ characters: data.results, page}));

    }
}