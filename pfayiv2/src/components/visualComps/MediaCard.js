import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

export default function MediaCard({character}) {
  return (
    <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                >
                  <CardMedia
                    component="img"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                    }}
                    image={character.image}
                    alt="random"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                        {character.name}
                    </Typography>
                    <Typography>
                      Gender : {character.gender}
                    </Typography>
                    <Typography>
                      Status : {character.status}
                    </Typography>
                    <Typography>
                      Specie : {character.species}
                    </Typography>
                  </CardContent>
                  <CardActions>
                    <Link to={`/characters:${id}`}>
                      <Button size="small">View</Button>
                    </Link>
                    <Button size="small">Edit</Button>
                  </CardActions>
                </Card>
  );
}
