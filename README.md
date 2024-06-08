
### R(Query)
```graphql
{
	getBooks(count: 3, offset: 0) {
    id
  	title
    author {
      id
    }
  }
}
```

### CUD(Mutation)
```graphql
mutation {
  createBook(input: {
    id: "4"
    title: "난중일기"
    year: 2024
    author: {
      id:"4",
      firstName: "이",
      lastName: "순신"
    }
  }) {
    id
    title
    year
    author {
      id
      firstName
      lastName
    }
  }
}


mutation {
    deleteBook(id: "1")
}
```