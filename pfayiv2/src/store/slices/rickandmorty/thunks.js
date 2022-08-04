import { characterApi } from "../../../api/rnmapi"
import { setCharacters, startLoadingCharacters, setCharacter } from "./rnmSlice"

export const getCharacters = ( page = 1 ) => {
    return async( dispatch, getState ) => {
        dispatch( startLoadingCharacters() );

        const { data } = await characterApi.get(`character/?page=${page}`)

        dispatch( setCharacters({ characters: data.results, page}));
    }
    
}
export const getCharacter = ( id ) => {
    return async( dispatch, getState ) => {
        dispatch( startLoadingCharacters() );
        
        const { data } = await characterApi.get(`character/${id}`)
        
        console.log("Asi llega axios", data);

        dispatch( setCharacter({ character: data}));
        
    }
}