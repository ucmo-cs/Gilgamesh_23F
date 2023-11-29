import Table from 'react-bootstrap/Table';

function ChangesTableComponent() {
  return (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Change Number</th>
              <th>Change Description</th>
              <th>Approval Status</th>
              <th>Change Date</th>
              <th>Change Start Time</th>
              <th>View Change Request</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>desc</td>
              <td>open</td>
              <td>00/00/0000</td>
              <td>00:00:00</td>
              <td>View Button</td>
            </tr>
            <tr>
              <td>2</td>
              <td>desc</td>
              <td>frozen</td>
              <td>00/00/0000</td>
              <td>00:00:00</td>
              <td>View Button</td>
            </tr>
            <tr>
              <td>3</td>
              <td>desc</td>
              <td>dept</td>
              <td>00/00/0000</td>
              <td>00:00:00</td>
              <td>View Button</td>
            </tr>
          </tbody>
        </Table>
      );
    }

export default ChangesTableComponent;