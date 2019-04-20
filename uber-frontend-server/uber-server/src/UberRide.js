import React, {Component} from 'react';
import axios from "axios";
import './UberRide.css';

class UberRide extends Component {

    constructor(props) {
        super(props);
        this.state = {list: [], listChanged: false};
        this.edit = this.editRow.bind(this);
        this.handleChange = this.handleChangeOnCell.bind(this);
        this.save = this.saveRow.bind(this);
        this.delete = this.deleteRow.bind(this);
    }

    componentDidMount() {
        let curr = this;
        axios.get('http://localhost:8080/uber-data/job').then(
            function (resp) {
                let data = resp.data;
                data.map(row => row['isEditing'] = false);
                curr.setState({list : data});
            }).catch(
            function (error) {
                console.log(error);
            }
        );
    }

    editRow(event) {
        let el = event.target;
        const id = el.getAttribute('value');
        let row = this.state.list[id];
        row.isEditing = true;
        this.setState({listChanged : true});
    }

    handleChangeOnCell(event) {
        let el = event.target;
        const id = el.getAttribute('row');
        let row = this.state.list[id];
        const field = el.getAttribute('field');
        row[field] = el.value;
        this.setState({list : this.state.list.splice(id, 1, row)});
    }

    saveRow(event) {
        let el = event.target;
        const id = el.getAttribute('value');
        let row = this.state.list[id];
        row.isEditing = false;
        this.setState({listChanged : false});
        let curr = this;
        const uuid = row['uuid'];
        axios.put('http://localhost:8080/uber-data/job/'+uuid, row).then(
            function (resp) {
                let data = resp.data;
                data.map(row => row['isEditing'] = false);
                curr.setState({list : data});
            }).catch(
            function (error) {
                console.log(error);
            }
        );
    }

    deleteRow(event) {
        console.log("Deleting");
    }

    header = ["Start Date", "End Date", "Category", "PickUp Point", "Destination", "Miles", "Purpose"];

    render() {
        if(this.state) {
        return (
            <table>
                <thead>
                <tr>
                    {this.header.map((h,i) => <th key={i}>{h}</th>)}
                    <th colSpan={2}>Actions</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.state.list.map((data, i) => {
                        if(!data.isEditing) {
                            return (
                                <tr key={i}>
                                    <td>{data.startDateOfRide}</td>
                                    <td>{data.endDateOfRide}</td>
                                    <td>{data.category}</td>
                                    <td>{data.pickUpPoint}</td>
                                    <td>{data.destination}</td>
                                    <td>{data.miles}</td>
                                    <td>{data.purpose}</td>
                                    <td className="round"><button onClick={this.edit} value={i}>Edit</button></td>
                                    <td className="round"><button onClick={this.delete}>Delete</button></td>
                                </tr>
                            )
                        } else {
                            return (
                                <tr key={i}>
                                    <td><input type="datetime-local" value={data.startDateOfRide} onChange={this.handleChange} field="startDateOfRide" row={i}/></td>
                                    <td><input type="datetime-local" value={data.endDateOfRide} onChange={this.handleChange} field="endDateOfRide" row={i}/></td>
                                    <td><input type="text" value={data.category} onChange={this.handleChange} field="category" row={i}/></td>
                                    <td><input type="text" value={data.pickUpPoint} onChange={this.handleChange} field="pickUpPoint" row={i}/></td>
                                    <td><input type="text" value={data.destination} onChange={this.handleChange} field="destination" row={i}/></td>
                                    <td><input type="number" step={0.001} value={data.miles} onChange={this.handleChange} field="miles" row={i}/></td>
                                    <td><input type="text" value={data.purpose} onChange={this.handleChange} field="purpose" row={i}/></td>
                                    <td colSpan={2} className="round"><button onClick={this.save} value={i}>Save</button></td>
                                </tr>
                            )
                        }

                    }
                    )
                }
                </tbody>
            </table>
        );
    } else {
            return <div></div>
        }

    }
}

export default UberRide;
