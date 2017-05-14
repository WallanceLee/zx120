package cn.edu.njupt.zx120.controller.realtime;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dao.OrgGPSDao;
import cn.edu.njupt.zx120.dto.admin.ReaderDto;
import cn.edu.njupt.zx120.dto.realtime.EventDto;
import cn.edu.njupt.zx120.dto.realtime.OrgResourceDto;
import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;
import cn.edu.njupt.zx120.dto.realtime.RecordLogDto;
import cn.edu.njupt.zx120.dto.realtime.ResourceDescriptorDto;
import cn.edu.njupt.zx120.model.OrgGPSModel;
import cn.edu.njupt.zx120.service.AmbulanceService;
import cn.edu.njupt.zx120.service.EventService;
import cn.edu.njupt.zx120.service.MemberService;
import cn.edu.njupt.zx120.service.OrganizationService;
import cn.edu.njupt.zx120.service.ReaderService;
import cn.edu.njupt.zx120.service.RecordLogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/14/17.
 */
@RestController("realTimeController")
@RequestMapping("/api/realtime")
public class RealTimeController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private OrgGPSDao orgGPSDao;

    @Autowired
    private RecordLogService recordLogService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map all() {
        Object organizationDtos = getOrgResource().get("entity");
        Object originalRecords = getOriginalRecord().get("entity");
        Object event = getEvents().get("entity");
        Map result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("organizations", organizationDtos);
        result.put("originalRecords", originalRecords);
        result.put("event", event);
        return result;
    }

    @RequestMapping(value = "/orgResource", method = RequestMethod.GET)
    public Map getOrgResource() {
        List<OrganizationDto> organizationDtos = organizationService.queryAll();
        List<OrgResourceDto> orgResourceDtos = new ArrayList<>();
        for (OrganizationDto organizationDto : organizationDtos) {

            OrgResourceDto orgResourceDto = new OrgResourceDto();
            orgResourceDto.setOrg(organizationDto);

            ResourceDescriptorDto ambulanceResource = new ResourceDescriptorDto();
            ambulanceResource.setResourceTotalNumber(ambulanceService.queryCountByOrgId(orgResourceDto.getOrg().getOrgId()));
            ambulanceResource.setResourceRemainNumber(ambulanceService.queryAvailableCountByOrgId(organizationDto.getOrgId()));
            ambulanceResource.setResourceOutNumber(ambulanceResource.getResourceTotalNumber()  - ambulanceResource.getResourceRemainNumber());
            orgResourceDto.setAmbulanceResource(ambulanceResource);

//            ResourceDescriptorDto memberResource = new ResourceDescriptorDto();
//            memberResource.setResourceTotalNumber(memberService.queryByOrdId(orgResourceDto.getOrg().getOrgId()).size());
//            memberResource.setResourceRemainNumber(memberService.queryAvailableByOrgId(organizationDto.getOrgId()).size());
//            memberResource.setResourceOutNumber(memberService.queryOutByOrgId(organizationDto.getOrgId()).size());
//            orgResourceDto.setMemberResource(memberResource);

            orgResourceDtos.add(orgResourceDto);

        }

        return resultMap(true, orgResourceDtos);

    }

    @RequestMapping(value = "/orgGPS", method = RequestMethod.POST)
    public void saveOrgGPS(@RequestBody String orgInfoList) {
        List<OrgGPSModel> orgGPSModels = JSON.parseArray(orgInfoList, OrgGPSModel.class);
        for (OrgGPSModel orgGPSModel : orgGPSModels) {
            OrgGPSModel oldOrgPSModel = orgGPSDao.queryByOrgId(orgGPSModel.getOrgId());
            if (oldOrgPSModel == null) {
                orgGPSDao.saveNewOrgGPS(orgGPSModel);
            } else {
                orgGPSDao.updateOrgGPS(orgGPSModel.getOrgId(), orgGPSModel.getLongitude(), orgGPSModel.getLatitude());
            }
        }
    }

    @RequestMapping(value = "/originalRecord", method = RequestMethod.GET)
    public Map getOriginalRecord() {
        List<RecordLogDto> recordLogDtos = recordLogService.queryAll();
        return resultMap(true, recordLogDtos);
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public Map getEvents() {
        List<EventDto> eventDtos = eventService.queryAll();
        List<String> eventMessages = new ArrayList<>();
        for (EventDto eventDto : eventDtos) {
            eventMessages.add(eventDto.toString());
        }
        return resultMap(true, eventMessages);
    }

    @RequestMapping(value = "/event1", method = RequestMethod.GET)
    public Map getEvents1() {
        List<EventDto> eventDtos = eventService.queryAll();
        List<Map<String, String>> eventMessages = new ArrayList<>();
        for (EventDto eventDto : eventDtos) {
            Map<String, String> event = new HashMap<>();
            event.put("event", eventDto.toString());
            eventMessages.add(event);
        }
        return resultMap(true, eventMessages);
    }

    @RequestMapping(value = "/readerStatus", method = RequestMethod.GET)
    public Map getReaderStatus() {
        List<ReaderDto> readerDtos = readerService.queryAll();
        return resultMap(true, readerDtos);
    }

}
