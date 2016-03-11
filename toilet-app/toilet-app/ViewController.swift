//
//  ViewController.swift
//  toilet-app
//
//  Created by Mauricio Salatino on 10/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate {
    var counterInt = 0
    var timer = NSTimer()
    
    var data = ["AAAA", "BBBB", "CCCC", "DDDD", "EEEE", "FFFF", "GGGG", "HHHH", "IIII"]
    
    func counter(){
        counterInt++
        timeLabel.text = String(counterInt)
    }
  
    override func viewDidLoad() {

        
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBOutlet weak var playButton: UIToolbar!
    
    @IBAction func playAction(sender: AnyObject) {

        timer = NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: Selector("counter"), userInfo: nil, repeats: true)
    }
    
    @IBOutlet weak var pauseAction: UIBarButtonItem!
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBOutlet weak var timeLabel: UILabel!
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return data.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let cell = UITableViewCell(style: .Default, reuseIdentifier: "Cell")
        cell.textLabel?.text = data[indexPath.row]
        return cell
    }
    
    
    @IBAction func pauseAction(sender: AnyObject) {
        timer.invalidate()
    }
    
    @IBAction func resetAction(sender: AnyObject) {
        timer.invalidate()
        counterInt = 0
        timeLabel.text = String(counterInt)
    }
    
    

}

