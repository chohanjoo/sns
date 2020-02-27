import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import ButtonAppBar from './component/Bar';
import Routes from './component/Routes';

function App() {
    return (
        <div className="App">
            <MuiThemeProvider>
                <ButtonAppBar/>
            </MuiThemeProvider>
            <Routes/>
        </div>
    );
}

export default App;
