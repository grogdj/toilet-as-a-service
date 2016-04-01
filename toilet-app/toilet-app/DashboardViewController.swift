//
//  DashboardViewController.swift
//  toilet-app
//
//  Created by Mauricio Salatino on 11/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class DashboardViewController: UIViewController, UITableViewDelegate, UITextFieldDelegate {
    
    var data = [1,2,3,4,5,6,7]
    
    @IBOutlet weak var dashboadTableView: UITableView!
    @IBOutlet weak var myTextField: UITextField!
    override func viewDidLoad() {
        
        self.myTextField.delegate = self
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    override func viewDidAppear(animated: Bool) {
        dashboadTableView.reloadData()
    }
    
    /*
    *  Basic table methods
    */
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return data.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let cell = UITableViewCell(style: .Default, reuseIdentifier: "Cell")

        cell.textLabel?.text = String(data[indexPath.row])
        return cell
    }

    
    /*
    * Adding swipe delete code to table view
    */
    
    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        return true
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if (editingStyle == UITableViewCellEditingStyle.Delete) {
            // handle delete (by removing the data from your array and updating the tableview)
            //removeAtIndex(indexPath.row) (from the array)
            data.removeAtIndex(indexPath.row)
            dashboadTableView.reloadData()
        }
    }
    
    
    

    /*
    * This two methods are for closing the keyboard when it bothers
    */
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(textfield : UITextField ) -> Bool {
        textfield.resignFirstResponder()
        return true;
    }
    

}
