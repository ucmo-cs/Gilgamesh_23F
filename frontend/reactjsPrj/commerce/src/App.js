import commerceBankLogo from './images/commerceBankLogo.png';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from './components/NavBar';
import Login from './files/LoginPage/Login';
import ChangesTableComponent from './components/ChangesTableComponent';



function App() {
  return (
    <div className="App">
      <NavBar />
      <ChangesTableComponent />
    </div>
  );
}

export default App;
