import classes from '../../components/cssFiles/HomePagePanel.module.css';
import { useState } from 'react';
import background1 from "../../images/background1.jpg";
import gsap from "gsap";
function PhotoPanel(props){
    //console.log(props);
    const [photo, setPhoto] = useState("https://i.ibb.co/C2Ysq38/background1.jpg");
    if(props.photo==="&FEMALE" && photo != "https://i.ibb.co/VSfyTcx/FEMALE.jpg"){
      setPhoto("https://i.ibb.co/VSfyTcx/FEMALE.jpg");

    }
    else if(props.photo === "&MALE" && photo != "https://i.ibb.co/kqSDPVs/MALE.jpg"){
      setPhoto("https://i.ibb.co/kqSDPVs/MALE.jpg");
    }
    return (
        <div>
   
<div className={classes.slideshowcontainer}>
<div className={classes.mySlides} className={classes.fade}>
  <img src={photo} style={{width : '100%'}}></img>
</div>
</div>
<br />

</div>
    );
}
export default PhotoPanel;