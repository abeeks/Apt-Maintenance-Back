import { BrowserRouter, Routes, Route } from "react-router-dom";
import {Container} from '@mui/material';
import {Request} from "./components/pages/Requests";
import {NoContent} from "./components/pages/NoConents";
import {UpdateRequest} from "./components/pages/UpdateRequest";
import {AddRequest} from "./components/pages/AddRequest";
function App() {

  return (
    <Container maxWidth="md">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Request />} />
          <Route path="/update/:id" element={<UpdateRequest />} />
          <Route path="/add" element={<AddRequest />} />
          <Route path="*" element={<NoContent />} />
        </Routes>
      </BrowserRouter>
     
    </Container>
  );
}

export default App;
