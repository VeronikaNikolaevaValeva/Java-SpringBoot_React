import Layout from "../components/layout/Layout";
import PhotoPanel from "../components/layout/PhotoPanel";
import ClothesPageBody from "../components/layout/ClothesPageBody";

function ClothesPage(props){
    const value = props.value;

    return (
        <Layout>
            <div>
                <br /> <br /> <br /> <br />
                <PhotoPanel photo ={props.value}/>
                <ClothesPageBody value = {props.value} />
            </div>
        </Layout>
        
    );
}

export default ClothesPage;