//
//  HomeBathroomsViewController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 06/04/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class HomeBathroomsViewController: UIViewController {

    var home: NSDictionary = NSDictionary();
    var baths: NSMutableArray = NSMutableArray();
    
    @IBOutlet weak var bathsTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadBathrooms()
    }
    
    func loadBathrooms(){
        let tbc = self.tabBarController  as! HomeDetailsTabBarController
        home = tbc.home
        tbc.title = home["name"] as? String
        // Do any additional setup after loading the view.
        let bathService = tbc.home["bathrooms"] as! String;
        NSURLSession.sharedSession().dataTaskWithURL(NSURL(string: bathService)!) { (data, response, error) -> Void in
            
            if let urlContent = data {
                do{
                    
                    self.baths = try NSJSONSerialization.JSONObjectWithData(urlContent, options: NSJSONReadingOptions.MutableContainers) as! NSMutableArray
                    dispatch_async(dispatch_get_main_queue(), { () -> Void in
                        self.bathsTable.reloadData()
                    })
                    
                } catch {
                    print("Error parsing JSON")
                }
            }
            
            }.resume()

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    // MARK: - Table view data source
     func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
     func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return baths.count
    }
    
     func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath)
        if let bathName = baths[indexPath.row]["name"]{
            cell.textLabel?.text = String(bathName!)
        } else{
            cell.textLabel?.text = String("No Name")
        }
        return cell
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
