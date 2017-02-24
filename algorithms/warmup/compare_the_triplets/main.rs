
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
    let mut s = get_input_line();
    let line1 = tokenize(&mut s);
    let mut s = get_input_line();
    let line2 = tokenize(&mut s);
    
    let mut a = 0;
    let mut b = 0;
    
    for i in 0..3 {
        let x = line1[i].parse::<i64>().unwrap();    
        let y = line2[i].parse::<i64>().unwrap();    
        
        if x > y {
            a += 1;
        }else if x < y {
            b += 1;
        }
        
    }
    
    println!("{} {}", a, b);
}
