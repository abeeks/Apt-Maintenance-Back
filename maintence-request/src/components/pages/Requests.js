import {RequestTable} from "/Users/student10/dev/spring-projects/maintenance-request-server/maintence-request/src/components/RequestTable.js";
import Button from '@mui/material/Button';
import {useNavigate } from "react-router-dom";

export const Request =() => {
    const navigate = useNavigate();

    function AddRequest(){
        navigate("/add")
    }

    return(
        <>
            <Button variant="outlined" onClick={e => AddRequest()}>Add Request</Button>
            <RequestTable />
        </>
    )
}
