# Crea as clases Java co plugin e indica no README.md o comando utilizado. (0,1 puntos)    
mvn clean generate-sources

# Lado propietario Book-Author
O lado propietario é Book porque ten a anotación: 

@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors", catalog = "bookdb", joinColumns = {
            @JoinColumn(name = "book_id", nullable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "author_id", nullable = false) })