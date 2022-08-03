import React, { useState } from "react";
import axios from "axios";
import Cards from "./Cards"


const AxiosExample = () => {

    const [pokemons, setPokemons] = useState([]);

    useState(() => {
        axios
        .get(process.env.REACT_BASE_APIBASE)
        .then((response) => {
            console.log(response);
            setPokemons(response.data.results);
        })
        .catch((error) => {
            console.log(error);
        });
    }, []);

    return(
        <section>
            {pokemons.map(pokemon => (<Cards name={ pokemon.name } url={ pokemon.url } /> )) }
        </section>
        )

}

export default AxiosExample