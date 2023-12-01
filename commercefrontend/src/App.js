import logo from './logo.svg';
import './App.css';
import {Container} from 'react-bootstrap';
import {Route} from 'react-router-dom';
import Detail from './pages/book/Detail';
import ChangeRequestList from './pages/ChangeRequestList';
import CreateChangeRequest from './pages/CreateChangeRequest';
import CompletedChanges from './pages/CompletedChanges';
import OutstandingChanges from './pages/OutstandingChanges';
import ChangeDetails from './pages/ChangeDetails';
import { AuthProvider } from './components/AuthContext';
import UserLogin from './pages/UserLogin';
import ChangeApproval from './pages/ChangeApproval'
import './components/Header.css'

function App() {
  return (
    <AuthProvider>
      <div>
        <Container>
          <Route path="/" exact={true} component={UserLogin}/>
          <Route path="/login" exact={true} component={UserLogin}/>
          <Route path="/create" exact={true} component={CreateChangeRequest}/>
          <Route path="/changerequest" exact={true} component={ChangeRequestList}/>
          <Route path="/book/:id" exact={true} component={Detail}/>
          <Route path="/completed" exact={true} component={CompletedChanges}/>
          <Route path="/outstanding" exact={true} component={OutstandingChanges}/>
          <Route path="/details/:id" exact={true} component={ChangeDetails}/>
          <Route path="/approval/:id" exact={true} component={ChangeApproval}/>
        </Container>    
      </div>
    </AuthProvider>
  );
}

export default App;
