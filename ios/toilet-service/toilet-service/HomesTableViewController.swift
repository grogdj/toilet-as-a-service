//
//  HomesTableViewController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 22/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

let serviceUrl = "http://localhost:8083/api/homes/"

class HomesTableViewController: UITableViewController {
    
    @IBOutlet var homesTable: UITableView!
    
    var homes: NSMutableArray = NSMutableArray();
    
    @IBAction func unwindToHomeList(segue: UIStoryboardSegue){
        print("Unwinding.... ")
        if(segue.sourceViewController .isKindOfClass(AddHomeViewController)){
            if let sourceViewController = segue.sourceViewController as? AddHomeViewController {
                let newHome = sourceViewController.newHome
                let newIndexPath = NSIndexPath(forRow: homes.count, inSection: 0)
                homes.addObject(newHome)
                homesTable.insertRowsAtIndexPaths([newIndexPath], withRowAnimation: .Bottom)
            }
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        NSURLSession.sharedSession().dataTaskWithURL(NSURL(string: serviceUrl)!) { (data, response, error) -> Void in
            if let urlContent = data {
                do{
                    
                    self.homes = try NSJSONSerialization.JSONObjectWithData(urlContent, options: NSJSONReadingOptions.MutableContainers) as! NSMutableArray
                    dispatch_async(dispatch_get_main_queue(), { () -> Void in
                        self.homesTable.reloadData()
                    })
                    
                } catch {
                    print("Error parsing JSON")
                }
            }
            
            }.resume()
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return homes.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath)
        if let homeName = homes[indexPath.row]["name"]{
            cell.textLabel?.text = String(homeName!)
        } else{
            cell.textLabel?.text = String("No Name")
        }
        return cell
    }
    
    
    
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    
    
    
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            
            if let id = homes[indexPath.row]["id"]{
                if let finalId = id{
                    let url = NSURL(string: "\(serviceUrl)\(finalId)")!
                    let request = NSMutableURLRequest(URL: url)
                    let session = NSURLSession.sharedSession()
                    
                    request.HTTPMethod = "DELETE"
                    request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
                    
                    // Make the DELETE call and handle it in a completion handler
                    session.dataTaskWithRequest(request, completionHandler: { ( data: NSData?, response: NSURLResponse?, error: NSError?) -> Void in
                        // Make sure we get an OK response
                        guard let realResponse = response as? NSHTTPURLResponse where
                            realResponse.statusCode == 204 else {
                                print("Not a 204 response")
                                return
                        }
                        
                        dispatch_async(dispatch_get_main_queue(), { () -> Void in
                            self.homes.removeObjectAtIndex(indexPath.row)
                            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
                            //                            self.homesTableView.reloadData()
                        })
                        
                        
                        
                    }).resume()
                }
            }else{
                print("Wrong ID");
            }
            
            
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    
    override func tableView(tableView: UITableView, willSelectRowAtIndexPath indexPath: NSIndexPath) -> NSIndexPath? {
        print(indexPath.row)
        return indexPath;
    }
    
    
    /*
     // Override to support rearranging the table view.
     override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {
     
     }
     */
    
    /*
     // Override to support conditional rearranging of the table view.
     override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
     // Return false if you do not want the item to be re-orderable.
     return true
     }
     */
    
    
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if let cell = sender as? UITableViewCell {
            let i = homesTable.indexPathForCell(cell)!.row
            print("data here -> "+String(i))
            
            let vc = segue.destinationViewController as! HomeDetailsTabBarController
            print("data here2 -> "+String(homes[i]))
            vc.home = homes[i] as! NSDictionary
            
        }
        
    }
    
    
}
