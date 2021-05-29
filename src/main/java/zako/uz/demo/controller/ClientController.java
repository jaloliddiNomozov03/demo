package zako.uz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zako.uz.demo.entity.*;
import zako.uz.demo.payload.*;
import zako.uz.demo.services.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private AccountantService accountantService;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private NounService nounService;
    @Autowired
    private TenderService tenderService;

    //region FileController
    @PostMapping("/file/save")
    public ResponseEntity<ApiResponse> superFileSave(@RequestParam(name = "file") MultipartFile multipartFile){
        return ResponseEntity.ok(attachmentService.save(multipartFile));
    }
    @DeleteMapping("/file/delete/{hashCode}")
    public ResponseEntity<ApiResponse> superFileDelete(@PathVariable String hashCode){
        return ResponseEntity.ok(attachmentService.delete(hashCode));
    }
    @GetMapping("/file/preview/{hashCode}")
    public ResponseEntity<InputStreamResource> superFilePreview(@PathVariable String hashCode, HttpServletResponse response) throws IOException, IOException {
        assert hashCode != null;
        return attachmentService.getFile(hashCode, response);
    }
    @GetMapping("/file/getPage")
    public ResponseEntity<Page<Attachment>> superFileGetPage(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(attachmentService.findFilePage(page, size));
    }
    //endregion

    //Accountant controller
    @PostMapping(value = "/accountant/save")
    public ResponseEntity<ApiResponse> accountantSave(@RequestBody AccountantRequest accountant){
        return ResponseEntity.ok(accountantService.saveAccountant(accountant));
    }
    @PutMapping("/accountant/update/{accountantId}")
    public ResponseEntity<ApiResponse> accountantUpdateById(@PathVariable Long accountantId,
                                                            @RequestBody AccountantRequest accountant){
        return ResponseEntity.ok(accountantService.updateAccountant(accountant, accountantId));
    }
    @DeleteMapping("/accountant/delete/{accountantId}")
    public ResponseEntity accountantDeleteById(@PathVariable Long accountantId){
        return ResponseEntity.ok(accountantService.deleteAccountant(accountantId));
    }
    @GetMapping("/accountant/get/{accountantId}")
    public ResponseEntity accountantGetById(@PathVariable Long accountantId){
        return ResponseEntity.ok(accountantService.getAccountantById(accountantId));
    }
    @GetMapping("/accountant/getAll")
    public ResponseEntity accountantGetAll(){
        return ResponseEntity.ok(accountantService.getAccountantsList());
    }
    // end

    //Advertisement controller
    @PostMapping("/advertisement/save")
    public ResponseEntity<ApiResponse> advertisementSave(@RequestBody AdvertisementRequest advertisement){
       return ResponseEntity.ok(advertisementService.saveAdvertisement(advertisement));
    }
    @PutMapping("/advertisement/update/{advertisementId}")
    public ResponseEntity<ApiResponse> advertisementUpdateById(@PathVariable Long advertisementId,
                                                                @RequestBody AdvertisementRequest advertisement){
        return ResponseEntity.ok(advertisementService.updateAdvertisement(advertisement, advertisementId));
    }
    @DeleteMapping("/advertisement/delete/{advertisementId}")
    public ResponseEntity advertisementDeleteById(@PathVariable Long advertisementId){
        return ResponseEntity.ok(advertisementService.deleteAdvertisement(advertisementId));
    }
    @GetMapping("/advertisement/get/{advertisementId}")
    public ResponseEntity advertisementGetById(@PathVariable Long advertisementId){
        return ResponseEntity.ok(advertisementService.getAdvertisementById(advertisementId));
    }
    @GetMapping("/advertisement/getAll")
    public ResponseEntity advertisementGetAll(){
        return ResponseEntity.ok(advertisementService.getAdvertisementsList());
    }
    // end
    //News controller
    @PostMapping("/news/save")
    public ResponseEntity<ApiResponse> newsSave(@RequestBody NewsReq news){
        return ResponseEntity.ok(newsService.saveNews(news));
    }
    @PutMapping("/news/update/{newsId}")
    public ResponseEntity<ApiResponse> newsUpdateById(@PathVariable Long newsId,
                                                      @RequestBody NewsReq news){
        return ResponseEntity.ok(newsService.updateNews(news, newsId));
    }
    @DeleteMapping("/news/delete/{newsId}")
    public ResponseEntity newsDeleteById(@PathVariable Long newsId){
        return ResponseEntity.ok(newsService.deleteNews(newsId));
    }
    @GetMapping("/news/get/{newsId}")
    public ResponseEntity newsGetById(@PathVariable Long newsId){
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }
    @GetMapping("/news/getAll")
    public ResponseEntity newsGetAll(){
        return ResponseEntity.ok(newsService.getNewsList());
    }
    // end

    //Noun controller
    @PostMapping("/noun/save")
    public ResponseEntity<ApiResponse> nounSave(@RequestBody NounRequest noun){
        return ResponseEntity.ok(nounService.saveNoun(noun));
    }
    @PutMapping("/noun/update/{nounId}")
    public ResponseEntity<ApiResponse> nounUpdateById(@PathVariable Long nounId,
                                                            @RequestBody NounRequest noun){
        return ResponseEntity.ok(nounService.updateNoun(noun, nounId));
    }
    @DeleteMapping("/noun/delete/{nounId}")
    public ResponseEntity nounDeleteById(@PathVariable Long nounId){
        return ResponseEntity.ok(nounService.deleteNoun(nounId));
    }
    @GetMapping("/noun/get/{nounId}")
    public ResponseEntity nounGetById(@PathVariable Long nounId){
        return ResponseEntity.ok(nounService.getNounById(nounId));
    }
    @GetMapping("/noun/getAll")
    public ResponseEntity nounsGetAll(){
        return ResponseEntity.ok(nounService.getNounsList());
    }
    // end
     //Noun controller
    @PostMapping("/tenders/save")
    public ResponseEntity<ApiResponse> tendersSave(@RequestBody TenderRequest tenders){
        return ResponseEntity.ok(tenderService.saveTender(tenders));
    }
    @PutMapping("/tenders/update/{tendersId}")
    public ResponseEntity<ApiResponse> tendersUpdateById(@PathVariable Long tendersId,
                                                            @RequestBody TenderRequest tenders){
        return ResponseEntity.ok(tenderService.updateTender(tenders, tendersId));
    }
    @DeleteMapping("/tenders/delete/{tendersId}")
    public ResponseEntity tendersDeleteById(@PathVariable Long tendersId){
        return ResponseEntity.ok(tenderService.deleteTender(tendersId));
    }
    @GetMapping("/tenders/get/{tendersId}")
    public ResponseEntity tendersGetById(@PathVariable Long tendersId){
        return ResponseEntity.ok(tenderService.getTendersById(tendersId));
    }
    @GetMapping("/tenders/getAll")
    public ResponseEntity tendersGetAll(){
        return ResponseEntity.ok(tenderService.getTendersList());
    }
    // end
}
