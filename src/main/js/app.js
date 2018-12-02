const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {characters: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/characters'}).done(response => {
            this.setState({characters: response.entity._embedded.characters});
    });
    }

    render() {
        return (
            <CharacterList characters={this.state.characters}/>
    )
    }
}

class CharacterList extends React.Component{
    render() {
        const characters = this.props.characters.map(character =>
            <Character key={character._links.self.href} character={character}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Race</th>
                    <th>Archetype</th>
                </tr>
                {characters}
                </tbody>
            </table>
        )
    }
}

class Character extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.character.characterName}</td>
                <td>{this.props.character.race}</td>
                <td>{this.props.character.archetype}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);