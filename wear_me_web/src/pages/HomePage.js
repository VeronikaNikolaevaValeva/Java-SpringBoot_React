import Layout from "../components/layout/Layout";
import userService from "../services/user.service";
import productService from "../services/product.service";
import ClothesPageBody from "../components/layout/ClothesPageBody";
import PhotoPanel from "../components/layout/PhotoPanel";

userService.getUserByUsername();
productService.getListOfNineProducts("1", "SNEAKERS");
productService.getListOfProductByGender("1", "FEMALE", "SNEAKERS"); 
productService.getListOfProductByGender("1", "MALE", "SNEAKERS");
function HomePage(){
    

    return (
        <Layout>
            <div>
                <br /> <br /> <br /> <br />
                <PhotoPanel photo = {"homePhoto"}/>
                <ClothesPageBody value={""}/>
            </div>
        </Layout>
        
    );
}

export default HomePage;