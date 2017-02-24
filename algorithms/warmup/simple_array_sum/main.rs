
///Simple function that gets the next line of input from stdin
fn get_input_line() -> String {
    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).expect("Failed to get input");
    buffer
}

///Takes a mutable reference to a string and returns a vector of tokens
fn get_input_as_tokens(s: &mut String) -> Vec<&str> {
    let split: Vec<&str> = s.split_whitespace().collect();
    split
}


fn main() {
    get_input_line();
    let mut sum = 0i64;

    let mut s = get_input_line();
    let split = get_input_as_tokens(&mut s);
    
    for token in split {
        sum += token.trim().parse::<i64>().unwrap();
    }

    println!("{}", sum);
}
