//
//  HomeDetailsViewController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 22/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class HomeDetailsViewController: UIViewController {
    
    
    @IBOutlet weak var homeNameText: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let tbc = self.tabBarController as! HomeDetailsTabBarController
        
        homeNameText.text = tbc.home["name"] as? String
        tbc.title = tbc.home["name"] as? String
        // Do any additional setup after loading the view.
        
        NSURLSession.sharedSession().dataTaskWithURL(NSURL(string: "\(serviceUrl)\(tbc.home["id"]!)")!) { (data, response, error) -> Void in
            
            if let urlContent = data {
                do{
                    
                    tbc.home = try NSJSONSerialization.JSONObjectWithData(urlContent, options: NSJSONReadingOptions.MutableContainers) as! NSMutableDictionary
                    dispatch_async(dispatch_get_main_queue(), { () -> Void in
                        // do something here with the new home object
                        if let r = response as? NSHTTPURLResponse {
                            let links = r.allHeaderFields["Link"] as! String
                            let seplinks = links.componentsSeparatedByString(",");
                            for i in 0 ..< seplinks.count {
                                let link = seplinks[i].componentsSeparatedByString(";")
                                
                                if link[1] == " rel=\"bathrooms\"" {
                                    let bathroomsLink = link[0]
                                    let range = bathroomsLink.startIndex.advancedBy(2)...bathroomsLink.endIndex.predecessor().predecessor()
                                    
                                    tbc.home["bathrooms"] = bathroomsLink.substringWithRange(range)
                                }
                                if link[1] == " rel=\"persons\"" {
                                    let personsLink = link[0]
                                    let range = personsLink.startIndex.advancedBy(2)...personsLink.endIndex.predecessor().predecessor()
                                    
                                    tbc.home["persons"] = personsLink.substringWithRange(range)
                                }
                                
                            }
                        }

                        print(tbc.home)
                    })
                    
                } catch {
                    print("Error parsing JSON")
                }
            }
            
       }.resume();
    

    
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
