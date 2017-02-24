fn get_input_line() -> String {
    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).expect("Failed to read from stdin");
    buffer
}

fn tokenize(s: &mut String) -> Vec<&str> {
    let split: Vec<&str> = s.split_whitespace().collect();
    split
}

fn main() {
    let mut line = get_input_line();
    let line = tokenize(&mut line);

    let mut sum: u64 = 0;
    let mut min: u64 = <u64>::max_value();
    let mut max: u64 = 0;
    for token in line {
        let num: u64 = token.trim().parse().unwrap();
        sum += num;
        if num < min { min = num }
        if num > max { max = num }
    }

    println!("{} {}", sum-min, sum-max);

}


