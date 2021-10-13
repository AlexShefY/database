# Курс основ программирования на МКН СПбГУ
## Проект 2: key-value база данных
### Terms of use

>[[How it works](C:\Users\1258734\IdeaProjects\pf-2021-kvdb-AlexShefY\description.md)]

>The program starts executing after starting the file pf-2021-diff-main.jar
>that is in the directory pf-2021-kvdb-AlexShefY\out\artifacts\pf_2021_kvdb_main_jar

>The program is launched in the format:
>> java -jar pf-2021-kvdb-main.jar
> 
>Then the program waits for one of the following operations:
>> add [file_data] [key] [value] - add key-value to the database "file_data"
>> (if the key already exists you will receive a corresponding message)
> 
>> extract [file_data] [key] - print the value corresponding to the
>> given key in "file_data" database(if the key does not exist you will receive a corresponding message)
> 
>> in [file_data] [key] - print whether such a key exists in "file_data" database
> 
>> out [file_data] - print all key-value pairs in "file_data" database
>> contained in the database
> 
>> remove [file_data] [key] - remove corresponding key in "file_data" database
>> (if the key does not exist you will receive a corresponding message)
> 
>> removeAll [file_data] - remove allkey-value pairs in the database "file_data"
> 
>> removeIf [file_data] regex [regex] - remove all keys matching
>> the given regular expression in "file_data" database
>
>> removeIf [file_data] length [>/</<=/>=/==] [length] - 
>> remove all keys whose length matches th condition in "file_data" database
> 
>> change [file_data] [key] [value] - change value for the
>> given key in "file_data" database(if the key does not exist you will receive a corresponding message)
> 
>> filterOut [file_data] regex [regex] - print all key-value pairs
>> whose key matching the given
>> regular expression in "file_data" database
> 
>> filterOut [file_data] length [>/</>=/<=/==] [length] - print all
>> key-value pairs such that the key`s length matches the 
>> condition in "file_data" database

> If your input not match this pattern? then the
> program print one of the following messages:
>> Invalid input
> 
>> Invalid amount of input