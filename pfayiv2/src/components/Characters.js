import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import { getCharacters, setCharacters } from "../store/slices/rickandmorty"
import MediaCard from './visualComps/MediaCard';


const pages = ['Home', 'Characters', 'About'];
const settings = ['Logout'];

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="github.com/youwannacry">
        My GitHub
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function Album() {
    const dispatch = useDispatch();
    const { isLoading, page, characters } = useSelector( state => state.rnm)
    console.log("Estos son los personajes", characters);

    useEffect(() => {
    dispatch( getCharacters(page) );
}, [page])

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <main>
        {/* Hero unit */}
        <Container sx={{ py: 8 }} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>
            {characters.map((character) => (
              <Grid item key={character.id} xs={12} sm={6} md={4}>
                <MediaCard character = {character} />
              </Grid>
            ))}
          </Grid>
        </Container>
      </main>
      {/* Footer */}
      <Box sx={{ bgcolor: 'background.paper', p: 6 }} component="footer">
        <Typography variant="h6" align="center" gutterBottom>
        </Typography>
        <Typography
          variant="subtitle1"
          align="center"
          color="text.secondary"
          component="p"
        >
        </Typography>
        <Copyright />
      </Box>
      {/* End footer */}
    </ThemeProvider>
  );
}