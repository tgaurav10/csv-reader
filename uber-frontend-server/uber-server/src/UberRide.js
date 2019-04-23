import React, {Component} from 'react';
import axios from "axios";
import './UberRide.css';
import Pagination from "react-paginating";
import moment from "moment";

class UberRide extends Component {

    constructor(props) {
        super(props);
        this.state = {list: [], listChanged: false, currentPage: 1};
        this.edit = this.editRow.bind(this);
        this.handleChange = this.handleChangeOnCell.bind(this);
        this.save = this.saveRow.bind(this);
        this.delete = this.deleteRow.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    componentDidMount() {
        let curr = this;
        axios.get('http://localhost:8080/uber-data/job').then(
            function (resp) {
                let data = resp.data;
                data.map(row => {
                    row['isEditing'] = false;
                    row['startDateOfRide'] = curr.formatDateResponse(row['startDateOfRide']);
                    row['endDateOfRide'] = curr.formatDateResponse(row['endDateOfRide']);
                    return row;
                });
                //console.log(data.length);
                curr.setState({list: data});
            }).catch(
            function (error) {
                console.log(error);
            }
        );
        curr.state.pages = curr.state.list / 25;
    }

    formatDateResponse(date) {
        return moment(date.toString(), "MM/DD/YYYY HH:mm").format("YYYY-MM-DDTHH:mm");
    }

    formatDateInput(date) {
        return moment(date.toString(), "YYYY-MM-DDTHH:mm").format("MM/DD/YYYY HH:mm");
    }



    editRow(event) {
        let el = event.target;
        const id = el.getAttribute('value');
        let row = this.state.list[id];
        row.isEditing = true;
        this.setState({listChanged: true});
    }

    handleChangeOnCell(event) {
        let el = event.target;
        const id = el.getAttribute('row');
        let row = this.state.list[id];
        const field = el.getAttribute('field');
        row[field] = el.value;
        let newList = this.state.list;
        newList[id] = row;
        this.setState({list: newList});
    }

    saveRow(event) {
        let el = event.target;
        const id = el.getAttribute('value');
        let row = this.state.list[id];
        row.isEditing = false;
        this.setState({listChanged: false});
        let curr = this;
        const uuid = row['uuid'];
        row['startDateOfRide'] = curr.formatDateInput(row['startDateOfRide']);
        row['endDateOfRide'] = curr.formatDateInput(row['endDateOfRide']);
        axios.put('http://localhost:8080/uber-data/job/' + uuid, row).then(
            function (resp) {
                let data = resp.data;
                data['isEditing'] = false;
                data['startDateOfRide'] = curr.formatDateResponse(data['startDateOfRide']);
                data['endDateOfRide'] = curr.formatDateResponse(data['endDateOfRide']);
                curr.state.list.splice(id, 1);
                let newList = curr.state.list;
                newList.push(data);
                curr.setState({list: newList});
            }).catch(
            function (error) {
                console.log(error);
            }
        );
    }

    deleteRow(event) {
        let el = event.target;
        const id = el.getAttribute('value');
        let row = this.state.list[id];
        let curr = this;
        const uuid = row['uuid'];
        axios.delete('http://localhost:8080/uber-data/job/' + uuid).then(
            function (resp) {
                let newList = curr.state.list;
                newList.splice(id, 1);
                curr.setState({list: newList});
            }).catch(
            function (error) {
                console.log(error);
            }
        );
    }

    handlePageChange = (page, e) => {
        this.setState({
            currentPage: page
        });
    };

    header = ["Start Date", "End Date", "Category", "PickUp Point", "Destination", "Miles", "Purpose"];

    render() {
        if (this.state) {
            const { currentPage } = this.state;
            const  total  = this.state.list.length;
            console.log(total);
            const limit = 25;
            let endIndex = currentPage * limit;
            let beginIndex = (currentPage-1) * limit;
            return (
                <div>
                    <table>
                        <thead>
                        <tr>
                            {this.header.map((h, i) => <th key={i}>{h}</th>)}
                            <th colSpan={2}>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.list.slice(beginIndex, endIndex).map((data, i) => {
                                    if (!data.isEditing) {
                                        return (
                                            <tr key={i}>
                                                <td>{data.startDateOfRide}</td>
                                                <td>{data.endDateOfRide}</td>
                                                <td>{data.category}</td>
                                                <td>{data.pickUpPoint}</td>
                                                <td>{data.destination}</td>
                                                <td>{data.miles}</td>
                                                <td>{data.purpose}</td>
                                                <td className="round">
                                                    <button onClick={this.edit} value={(currentPage-1) * limit + i}>Edit</button>
                                                </td>
                                                <td className="round">
                                                    <button onClick={this.delete} value={(currentPage-1) * limit + i}>Delete</button>
                                                </td>
                                            </tr>
                                        )
                                    } else {
                                        return (
                                            <tr key={i}>
                                                <td><input type="datetime-local" value={data.startDateOfRide}
                                                           onChange={this.handleChange} field="startDateOfRide" row={(currentPage-1) * limit + i}/>
                                                </td>
                                                <td><input type="datetime-local" value={data.endDateOfRide}
                                                           onChange={this.handleChange} field="endDateOfRide" row={(currentPage-1) * limit + i}/></td>
                                                <td><input type="text" value={data.category} onChange={this.handleChange}
                                                           field="category" row={(currentPage-1) * limit + i}/></td>
                                                <td><input type="text" value={data.pickUpPoint} onChange={this.handleChange}
                                                           field="pickUpPoint" row={(currentPage-1) * limit + i}/></td>
                                                <td><input type="text" value={data.destination} onChange={this.handleChange}
                                                           field="destination" row={(currentPage-1) * limit + i}/></td>
                                                <td><input type="number" step={0.001} value={data.miles}
                                                           onChange={this.handleChange} field="miles" row={(currentPage-1) * limit + i}/></td>
                                                <td><input type="text" value={data.purpose} onChange={this.handleChange}
                                                           field="purpose" row={(currentPage-1) * limit + i}/></td>
                                                <td colSpan={2} className="round">
                                                    <button onClick={this.save} value={(currentPage-1) * limit + i}>Save</button>
                                                </td>
                                            </tr>
                                        )
                                    }

                                }
                            )
                        }
                        </tbody>
                    </table>

                    <Pagination
                        total={total}
                        limit={limit}
                        pageCount={10}
                        currentPage={currentPage}>
                        {({
                              pages,
                              currentPage,
                              hasNextPage,
                              hasPreviousPage,
                              previousPage,
                              nextPage,
                              totalPages,
                              getPageItemProps
                          }) => (
                            <div id="nav" className="pagination">
                                <button
                                    {...getPageItemProps({
                                        pageValue: 1,
                                        onPageChange: this.handlePageChange
                                    })}
                                >
                                    first
                                </button>

                                {hasPreviousPage && (
                                    <button
                                        {...getPageItemProps({
                                            pageValue: previousPage,
                                            onPageChange: this.handlePageChange
                                        })}
                                    >
                                        {'<'}
                                    </button>
                                )}

                                {pages.map(page => {
                                    let activePage = null;
                                    if (currentPage === page) {
                                        activePage = { backgroundColor: '#fdce09' };
                                    }
                                    return (
                                        <button
                                            {...getPageItemProps({
                                                pageValue: page,
                                                key: page,
                                                style: activePage,
                                                onPageChange: this.handlePageChange
                                            })}
                                        >
                                            {page}
                                        </button>
                                    );
                                })}

                                {hasNextPage && (
                                    <button
                                        {...getPageItemProps({
                                            pageValue: nextPage,
                                            onPageChange: this.handlePageChange
                                        })}
                                    >
                                        {'>'}
                                    </button>
                                )}

                                <button
                                    {...getPageItemProps({
                                        pageValue: totalPages,
                                        onPageChange: this.handlePageChange
                                    })}
                                >
                                    last
                                </button>
                            </div>
                        )}
                    </Pagination>
                </div>
            );
        }
        else {
            return <div></div>
        }

    }
}

export default UberRide;
