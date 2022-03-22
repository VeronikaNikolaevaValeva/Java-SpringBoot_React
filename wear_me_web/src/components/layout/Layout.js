import MainPageNavigation from "./MainPageNavigation";

function Layout(props){ 

    return (
        <div>
            <MainPageNavigation />
            <main>
                {props.children}
            </main>
        </div>
    );
}

export default Layout;