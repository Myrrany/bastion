const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {characters: []};
    }

    componentDidMount() {
        console.log("I try to get... ");
        fetch('http://localhost:8080/api/characters', {
            mode: 'cors',
            method: 'get',
            credentials: "same-origin",
            headers: {
                "Accept": "application/json"
            },
        })
            .then((response) => response.json())
            .then(json => this.setState({characters: json.ArrayList}))
            .then(json => {
                console.log(json)
            });


    }

    render() {
        const { characters = [] } = this.state;
        return (
            <ErrorBoundary>
                <CharacterList characters={characters}/>
            </ErrorBoundary>
        )
    }
}

class CharacterList extends React.Component {
    render() {
        const characters = this.props.characters.map(character =>
            <Character key={character.id} character={character}/>
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

class Character extends React.Component {
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

class ErrorBoundary extends React.Component {
    constructor(props) {
        super(props);
        this.state = {hasError: false};
    }

    static getDerivedStateFromError(error) {
        // Update state so the next render will show the fallback UI.
        return {hasError: true};
    }

    componentDidCatch(error, info) {
        // You can also log the error to an error reporting service
        console.log(error, info);
    }

    render() {
        if (this.state.hasError) {
            // You can render any custom fallback UI
            return <h1>Something went wrong.</h1>;
        }

        return this.props.children;
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
);