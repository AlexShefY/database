# Курс основ программирования на МКН СПбГУ
## Проект 2: key-value база данных
### Terms of use
>The program starts executing after starting the file pf-2021-diff-main.jar
>that is in the directory pf-2021-kvdb-AlexShefY\out\artifacts\pf_2021_kvdb_main_jar

>The program is launched in the format:
>> java -jar pf-2021-kvdb-main.jar start
> 
>Then the program waits for one of the following operations:
>> add [key] [value] - add key-value to the database
>> (if the key already extsts you will receive a corresponding message)
> 
>> extract [key] - print the value corresponding to the
>> given key(if the key does not exist you will receive a corresponding message)
> 
>> in [key] - print whether such a key exists
> 
>> out - print all key-value pairs
>> contained in the database
> 
>> remove [key] - remove corresponding key
>> (if the key does not exist you will receive a corresponding message)
> 
>> removeAll - remove allkey-value pairs in the database
> 
>> removeIf regex [regex] - remove all keys matching
>> the given regular expression
>
>> removeIf length [>/</<=/>=/==] [length] - 
>> remove all keys whose length matches th condition  
> 
>> change [key] [value] - change value for the
>> given key (if the key does not exist you will receive a corresponding message)
> 
>> filterOut regex [regex] - print all key-value pairs
>> whose key matching the given
>> regular expression
> 
>> filterOut length [>/</>=/<=/==] [length] - print all
>> key-value pairs such that the key`s length matches the 
>> condition

> If your input not match this pattern? then the
> program print one of the following messages:
>> Invalid input
> 
>> Invalid amount of input