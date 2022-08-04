import { BrowserRouter, Routes, Route } from 'react-router-dom';
import  {Home, Album, ItemDetail, About} from "../components"
import ResponsiveAppBar from "../components/visualComps/ResponsiveAppBar"

export const AppRouter = () => {

  return (
    <BrowserRouter>
    <ResponsiveAppBar/>
          <Routes>
            <Route path = "/" element = {<Home/>} />
            <Route path = "/about" element = {<About/>} />
            <Route path = "/characters" element = {<Album/>} />
            <Route path = "/character/:id" element = {<ItemDetail/>} />
          </Routes>
      </BrowserRouter>
  )
} 