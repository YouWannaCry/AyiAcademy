import React, { useEffect, useState} from "react";
import PokemonService from "./PokemonService";


const Fetch = () =>{
    const [pokemons, setPokemons] = useState()

    useEffect(() => {
        getPokemons()
    }, []);

        const getPokemons = async () =>{
            const response = await  PokemonService
            .getPokemons()
            .catch((e) => console.log(e))
            

            setPokemons(response.results);
        }
        console.log(pokemons);
}

export default Fetch;