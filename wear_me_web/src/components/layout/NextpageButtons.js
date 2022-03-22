import { useState } from 'react';
import {useRef} from 'react';
import classes from "../cssFiles/nextPageButton.module.css";
import productService from "../../services/product.service";

function NextPageButtons(){
    const [page, setPage] = useState(1);
    
function NextPageFunction(){
    setPage(page+1);
    nineProducts();
}
function PrevPageFunction(){
    if(page>1){
        setPage(page-1);
        nineProducts();
    }
}
function nineProducts(){
    productService.getListOfNineProducts(page);
   
}
	    return (
<div className={classes.formatPosition}>

<button onClick = {PrevPageFunction} className={classes.formatbutton}><a className={classes.format} className={classes.previousround}>&#8249;</a></button>
<button onClick = {NextPageFunction} className={classes.formatbutton}><a className={classes.format} className={classes.nextround}>&#8250;</a></button>

<br /> <br /> <br />
</div>

    );
}
export default NextPageButtons;