import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { getCharacter } from "../store/slices/rickandmorty";
import { Container, Grid, Box, CircularProgress } from "@mui/material";

export const ItemDetail = () => {
  const dispatch = useDispatch();
  
  let { isLoading, character } = useSelector((state) => state.rnm);

  const { id } = useParams();
  
  useEffect(() => {
    dispatch(getCharacter(id));
  }, [dispatch, id]);

  return (
    <section>
      {character && (
        <Container fixed sx={{ paddingTop: 12, paddingBottom: 4 }}>
          <Grid container>
            <ItemDetail
              id={character.id}
              name={character.name}
              pictureUrl={character.image}
            />
          </Grid>
        </Container>
      )}
    </section>
  );
};
